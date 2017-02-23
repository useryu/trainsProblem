package com.fisher.graph;

public class Logger {
	
	public static Logger getInstance() {
		return new Logger();
	}
	
	public void info(String msg) {
		System.out.println(msg);
	}

}
