package com.fisher.graph.input;

import java.util.List;

public class GraphDef {
	
	public static final int NO_PATH=-1;
	
	private int[][] map;

	private List<String> nodeNames;
	
	public GraphDef() {
	}
	
	public int getNodeIndexByName(String name) {
		int index=0;
		for(String n:nodeNames) {
			if(name.equals(n)) {
				return index;
			}
			index++;
		}
		return -1;
	}
	
	public String getNodeNameAt(int index) {
		return this.nodeNames.get(index);
	}
	
	public int[] getVectorsFor(String nodeName) {
		return map[this.getNodeIndexByName(nodeName)];
	}

	public int[][] getMap() {
		return map;
	}

	public void setMap(int[][] map) {
		this.map = map;
	}

	public List<String> getNodeNames() {
		return nodeNames;
	}

	public void setNodeNames(List<String> nodeNames) {
		this.nodeNames = nodeNames;
	}
	
}
