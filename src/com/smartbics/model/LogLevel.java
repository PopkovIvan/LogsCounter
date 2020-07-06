package com.smartbics.model;

public enum LogLevel {
	ERROR,
	WARN,
	DEBUG,
	INFO
	;
	
	public static LogLevel fromString(String level) {
        switch(level) {
        case "ERROR":
            return ERROR;
        case "WARN":
            return WARN;
        case "DEBUG":
        	return DEBUG;
    	default:
        case "INFO":
        	return INFO;
        }
    }
	
	public static String toString(LogLevel level) {
		switch(level) {
		case ERROR:
			return "ERROR";
		case WARN:
			return "WARN";
		case DEBUG:
			return "DEBUG";
		case INFO:
			return "INFO";
		default:
			return "";
		}
	}
};
