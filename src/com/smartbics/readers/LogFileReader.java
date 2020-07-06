package com.smartbics.readers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import com.smartbics.model.Log;

public class LogFileReader {

	private String logFolderPath;
	private LinkedList<File> logFiles;
	private Scanner logFileScanner;
	
	public LogFileReader(String logFolderPath) {
		this.logFolderPath = logFolderPath;
		this.logFiles = new LinkedList<File>();
		this.readLogFiles();
	}
	
	protected void readLogFiles() {
		File files = new File(logFolderPath);
		File newFiles[] = files.listFiles();
		for(File file: newFiles) {
			logFiles.add(file);
		}
		
		ensureLogFileOpen();
	}
	
	public Log readNextLog() {
		Log newLog = Log.readFrom(logFileScanner.nextLine());
		ensureLogFileOpen();
		
		return newLog;
	}
	
	protected void ensureLogFileOpen() {
		try {
			if (logFileScanner == null) {
				logFileScanner = new Scanner(logFiles.pop(), "UTF-8");
			}
			if (!logFileScanner.hasNextLine()) {
				logFileScanner.close();
				if (logFiles.size() != 0) {
					logFileScanner = new Scanner(logFiles.pop());
				}
			}
		} catch (FileNotFoundException e) {
			if (logFiles.size() != 0) {
				ensureLogFileOpen();
			}
		}
	}
	
	public boolean hasNextLog() {
		try {
			return logFileScanner.hasNextLine();
		} catch (IllegalStateException e) {
			return false;
		}
	}

	public String getLogFolderPath() {
		return logFolderPath;
	}

	public void setLogFolderPath(String logFolderPath) {
		this.logFolderPath = logFolderPath;
	}

	public LinkedList<File> getLogFiles() {
		return logFiles;
	}

	public void setLogFiles(LinkedList<File> logFiles) {
		this.logFiles = logFiles;
	}
}
