package com.fisher.graph;

import java.util.List;

public class ShortestPathBean {

	private int shortestPathDistences = Integer.MAX_VALUE;
	private List<String> shortestPath;
	private String startNode;
	private String endNode;

	public int getShortestPathDistences() {
		return shortestPathDistences;
	}

	public void setShortestPathDistences(int shortestPathDistences) {
		this.shortestPathDistences = shortestPathDistences;
	}

	public List<String> getShortestPath() {
		return shortestPath;
	}

	public void setShortestPath(List<String> shortestPath) {
		this.shortestPath = shortestPath;
	}

	public String getStartNode() {
		return startNode;
	}

	public void setStartNode(String startNode) {
		this.startNode = startNode;
	}

	public String getEndNode() {
		return endNode;
	}

	public void setEndNode(String endNode) {
		this.endNode = endNode;
	}

}
