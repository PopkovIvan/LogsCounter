package com.smartbics.model;

import java.util.Arrays;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;  

public class Log {
	protected Date date;
	protected LogLevel level;
	protected String message;
	
	public Log() {
		this.date = new Date(0);
		this.level = LogLevel.DEBUG;
		this.message = "";
	}
	
	public Log(Date Date, LogLevel level, String message) {
		this.date = Date;
		this.level = level;
		this.message = message;
	}
	
	public static Log readFrom(String fromString) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(LogDateFormat.GMT());
		Log newLog = new Log();
		
		String[] logData = fromString.split(";");
		if (logData.length < 2) {
			return newLog;
		}
		
		newLog.setLevel(LogLevel.fromString(logData[1]));
		newLog.setMessage(String.join("", Arrays.copyOfRange(logData, 2, logData.length - 1)));//in case of ';' character in log message
		
		try {
			newLog.setDate(dateFormat.parse(logData[0]));
		} catch (ParseException e) {
			System.out.println("Invalid format of date");//leave a default value
			e.printStackTrace();
		}
		
		return newLog;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date Date) {
		this.date = Date;
	}

	public LogLevel getLevel() {
		return level;
	}

	public void setLevel(LogLevel level) {
		this.level = level;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
