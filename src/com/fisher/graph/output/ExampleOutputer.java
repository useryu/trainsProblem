package com.fisher.graph.output;

import java.io.PrintStream;

public class ExampleOutputer extends OutPuter{
	
	public ExampleOutputer(PrintStream out) {
		super(out);
	}
	
	public void printResult(String no,String result) {
		out.printf("Output #%s: %s",no,result);
		out.println();
	}

}
