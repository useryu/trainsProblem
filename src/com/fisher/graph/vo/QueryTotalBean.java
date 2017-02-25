package com.fisher.graph.vo;

public class QueryTotalBean {

	private String end;
	private int total=0;

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public int getTotal() {
		return total;
	}


	public void addOne() {
		this.total++;
	}

}
