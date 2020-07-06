package com.smartbics.readers;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.smartbics.model.Log;
import com.smartbics.model.LogLevel;

public class LogCount {
	private LogFileReader reader;
	private TreeMap<Date, Integer> logCount;
	private long interval;
	private LogLevel logLevelToCount;
	
	public LogCount(LogFileReader reader, long interval, LogLevel logLevelToCount) {
		this.reader = reader;
		this.interval = interval;
		this.logLevelToCount = logLevelToCount;
		this.logCount = new TreeMap<Date, Integer>();
	}
	
	public void readLogs() {
		while(reader.hasNextLog()) {
			count(reader.readNextLog());
		}
	}
		
	private void count(Log log) {
		if (log.getLevel() != logLevelToCount) {
			return;
		}
		
		Date currDate = new Date(log.getDate().getTime() - (log.getDate().getTime() % interval));
		Integer count = logCount.get(currDate);
		
		if (count != null) {
			logCount.put(currDate, ++count);
		} else {
			logCount.put(currDate, 1);
		}
	}
	
	public void outputLogCount(String outputFile) throws IOException {
		StringBuilder logCountString = new StringBuilder("");
			
		SimpleDateFormat dateFormatFrom = new SimpleDateFormat("yyyy-MM-dd, HH.mm");
		SimpleDateFormat dateFormatTo = new SimpleDateFormat("-HH.mm");
		
		FileWriter fileWriter = new FileWriter(outputFile);
		
		for(Map.Entry<Date, Integer> entry : logCount.entrySet()) {
			Date date = entry.getKey();
			Integer count = entry.getValue();
			
			logCountString.append(dateFormatFrom.format(date));
			logCountString.append(dateFormatTo.format(new Date(date.getTime() + interval)));
			logCountString.append(" Колличество " + LogLevel.toString(logLevelToCount) + ": " + count.toString() + '\n');
			
			fileWriter.write(logCountString.toString());
			
			logCountString.setLength(0);
		}
		
		fileWriter.close();
	}

	public LogFileReader getReader() {
		return reader;
	}

	public void setReader(LogFileReader reader) {
		this.reader = reader;
	}

	public long getInterval() {
		return interval;
	}

	public void setInterval(long interval) {
		this.interval = interval;
	}

	public LogLevel getLogLevelToCount() {
		return logLevelToCount;
	}

	public void setLogLevelToCount(LogLevel logLevelToCount) {
		this.logLevelToCount = logLevelToCount;
	}

	public TreeMap<Date, Integer> getLogCount() {
		return logCount;
	}
}
