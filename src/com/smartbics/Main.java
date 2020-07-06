package com.smartbics;

import java.io.IOException;

import com.smartbics.model.LogLevel;
import com.smartbics.readers.*;

public class Main {

	public static void main(String[] args) {
		LogFileReader reader = new LogFileReader(args[0]);
		LogCount countLog = new LogCount(reader, Long.parseLong(args[1]), LogLevel.fromString(args[2].toUpperCase()));
		
		countLog.readLogs();
		
		try {
			countLog.outputLogCount(args[0] + "Statistics.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
