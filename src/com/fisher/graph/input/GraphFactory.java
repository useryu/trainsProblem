package com.fisher.graph.input;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class GraphFactory {

	public GraphFactory() {
	}
	
	public GraphDef buildGraph(Properties input) {
		GraphDef graph = new GraphDef();
		String stationStr = input.getProperty("stations");
		String[] stationArr = stationStr.split(",");
		List<String> nodeNames = new ArrayList<String>();
		for(String station:stationArr) {
			nodeNames.add(station.trim());
		}
		graph.setNodeNames(nodeNames);
		String pathStr = input.getProperty("path");
		String[] pathArr = pathStr.split(",");
		int[][] pathMap = new int[nodeNames.size()][nodeNames.size()];
		for(int xIndex=0;xIndex<nodeNames.size();xIndex++) {
			for(int yIndex=0;yIndex<nodeNames.size();yIndex++) {
				pathMap[xIndex][yIndex] = GraphDef.NO_PATH;
			}			
		}
		for(String path:pathArr) {
			path = path.trim();
			String from = getFrom(path);
			String to = getTo(path);
			int distences = getDistences(path);
			pathMap[graph.getNodeIndexByName(from)][graph.getNodeIndexByName(to)]=distences;
		}
		graph.setMap(pathMap);
		return graph;
	}
	
	public List<String> buildPath(String pathStr){
		List<String> result = new ArrayList<String>();
		String[] pathArr = pathStr.split("-");
		for(String path:pathArr) {
			result.add(path);
		}
		return result;
	}

	private int getDistences(String path) {
		return Integer.parseInt(path.substring(2));
	}

	private String getTo(String path) {
		return path.substring(1, 2);
	}

	private String getFrom(String path) {
		return path.substring(0, 1);
	}
}
