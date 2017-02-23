package com.fisher.graph;

import java.util.ArrayList;
import java.util.List;

public class GraphDef {
	
	private int[][] map = {
		{-1, 5, -1, 5, 7}, 
		{-1, -1, 4, -1, -1},
		{-1, -1, -1, 8, 2},
		{-1, -1, 8, -1, 6},
		{-1, 3, -1, -1, -1},
    };

	private List<String> nodeNames;
	
	public GraphDef() {
		nodeNames=new ArrayList<String>(5);
		nodeNames.add("A");
		nodeNames.add("B");
		nodeNames.add("C");
		nodeNames.add("D");
		nodeNames.add("E");
	}
	
	public String getNodeNameAt(int index) {
		return this.nodeNames.get(index);
	}
	
	public int[] getVectorsFor(String nodeName) {
		for(int index=0;index<nodeNames.size();index++) {
			if(nodeName.equals(nodeNames.get(index))) {
				return map[index];
			}
		}
		return null;
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
