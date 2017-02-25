package com.fisher.graph.log;

public class Logger {
	
	private boolean debug=false;
	
	public static Logger getInstance() {
		return new Logger();
	}
	
	public void debug(String msg) {
		if(debug) {
			System.out.println(msg);
		}
	}

}
