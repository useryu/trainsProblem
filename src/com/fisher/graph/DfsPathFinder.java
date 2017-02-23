package com.fisher.graph;

import java.util.ArrayList;
import java.util.List;

public class DfsPathFinder {

	private GraphDef graph;
	private static Logger logger = Logger.getInstance();

	public DfsPathFinder(GraphDef graph) {
		this.graph = graph;
	}

	public void findWithShortestDistences(List<String> path, int totalDistences, ShortestPathBean shortestPathBean) {
		String lastNodeName=path.get(path.size()-1);
        if (lastNodeName.equals(shortestPathBean.getEndNode()) && totalDistences < shortestPathBean.getShortestPathDistences() && totalDistences > 0) {
        	shortestPathBean.setShortestPath(path);
        	shortestPathBean.setShortestPathDistences(totalDistences);
            return;
        }

		int[] vectors = graph.getVectorsFor(lastNodeName);
		if(vectors==null) {
			logger.info("Didn't found node by name:"+lastNodeName);
			return;
		}
		for (int i = 0; i < vectors.length; i++) {
			if (vectors[i] > 0) {
				String nodeName = graph.getNodeNameAt(i);
                if (path.contains(nodeName))
                    continue;
				List<String> newPath = new ArrayList<String>();
				newPath.addAll(path);
				newPath.add(nodeName);
				int newDistences = totalDistences+vectors[i];
				findWithShortestDistences(newPath, newDistences , shortestPathBean);
			}
		}
	}
	
	public void findWithMaxDistences(String end, List<String> path, int totalDistences, int maxDistences) {
		if (totalDistences >= maxDistences)
			return;
		
		if (path.size() > 1 && path.get(path.size()-1).equals(end)) {
			logger.info("max distences:");
			logger.info(path+","+totalDistences);
		}
		
		String lastNodeName=path.get(path.size()-1);
		int[] vectors = graph.getVectorsFor(lastNodeName);
		if(vectors==null) {
			logger.info("Didn't found node by name:"+lastNodeName);
			return;
		}
		for (int i = 0; i < vectors.length; i++) {
			if (vectors[i] > 0) {
				String nodeName = graph.getNodeNameAt(i);
				List<String> newPath = new ArrayList<String>();
				newPath.addAll(path);
				newPath.add(nodeName);
				int newDistences = totalDistences+vectors[i];
				findWithMaxDistences(end, newPath, newDistences , maxDistences);
			}
		}
	}
	
	public void findWithMaxStops(String end, List<String> path, int maxStops) {
		if (path.size() - 1 > maxStops)
			return;
		
		if (path.size() > 1 && path.get(path.size()-1).equals(end)) {
			logger.info(path+","+(path.size()-1));
			return;
		}
		
		String lastNodeName=path.get(path.size()-1);
		int[] vectors = graph.getVectorsFor(lastNodeName);
		if(vectors==null) {
			logger.info("Didn't found node by name:"+lastNodeName);
			return;
		}
		for (int i = 0; i < vectors.length; i++) {
			if (vectors[i] > 0) {
				String nodeName = graph.getNodeNameAt(i);
				List<String> newPath = new ArrayList<String>();
				newPath.addAll(path);
				newPath.add(nodeName);
				findWithMaxStops(end, newPath, maxStops);
			}
		}
	}
	
	public void findWithExactStops(String end, List<String> path, int exactStops) {
		if (path.size() - 1 > exactStops)
			return;
		
		if ((path.size()-1) == exactStops && path.get(path.size()-1).equals(end)) {
			logger.info("Exact stops:");
			logger.info(path+","+(path.size()-1));
			return;
		}
		
		String lastNodeName=path.get(path.size()-1);
		int[] vectors = graph.getVectorsFor(lastNodeName);
		if(vectors==null) {
			logger.info("Didn't found node by name:"+lastNodeName);
			return;
		}
		for (int i = 0; i < vectors.length; i++) {
			if (vectors[i] > 0) {
				String nodeName = graph.getNodeNameAt(i);
				List<String> newPath = new ArrayList<String>();
				newPath.addAll(path);
				newPath.add(nodeName);
				findWithExactStops(end, newPath, exactStops);
			}
		}
	}

	public static void main(String[] args) {
		GraphDef graphDef = new GraphDef();
		DfsPathFinder finder = new DfsPathFinder(graphDef);
//		//6
//		List<String> path = new ArrayList<String>();
//		path.add("C");
//		finder.findWithMaxStops("C", path , 3);
//		//7
//		List<String> path7 = new ArrayList<String>();
//		path7.add("A");
//		finder.findWithExactStops("C", path7 , 4);
//		//10
//		List<String> path10 = new ArrayList<String>();
//		path10.add("C");
//		finder.findWithMaxDistences("C", path10, 0 , 30);
//		//8
//		List<String> path8 = new ArrayList<String>();
//		ShortestPathBean bean8 = new ShortestPathBean();
//		bean8.setStartNode("A");
//		bean8.setEndNode("C");
//		path8.add(bean8.getStartNode());
//		finder.findWithShortestDistences(path8, 0 , bean8 );
//		if(bean8.getShortestPathDistences()==Integer.MAX_VALUE) {
//			logger.info("Do not found path between "+bean8.getStartNode()+" and "+bean8.getEndNode());
//		} else {
//			logger.info(bean8.getShortestPath()+","+bean8.getShortestPathDistences());
//		}
		//9
		List<String> path9 = new ArrayList<String>();
		ShortestPathBean bean9 = new ShortestPathBean();
		bean9.setStartNode("B");
		bean9.setEndNode("B");
		path9.add(bean9.getStartNode());
		finder.findWithShortestDistences(path9, 0 , bean9 );
		if(bean9.getShortestPathDistences()==Integer.MAX_VALUE) {
			logger.info("Do not found path between "+bean9.getStartNode()+" and "+bean9.getEndNode());
		} else {
			logger.info(bean9.getShortestPath()+","+bean9.getShortestPathDistences());
		}
	}
}
