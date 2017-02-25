package com.fisher.graph.output;

import java.io.PrintStream;

public class OutPuter {
	
	protected PrintStream out;
	
	public OutPuter(PrintStream out) {
		this.out=out;
	}
	
	public void print(String msg) {
		out.println(msg);
	}

}
