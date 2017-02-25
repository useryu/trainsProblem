package com.fisher.graph.finder;

import java.util.ArrayList;
import java.util.List;

import com.fisher.graph.input.GraphDef;
import com.fisher.graph.log.Logger;
import com.fisher.graph.vo.QueryTotalBean;
import com.fisher.graph.vo.ShortestPathBean;

public class DfsPathFinder implements PathFinder{

	private static final String NO_SUCH_ROUTE = "NO SUCH ROUTE";
	private GraphDef graph;
	private static Logger logger = Logger.getInstance();

	public DfsPathFinder(GraphDef graph) {
		this.graph = graph;
	}
	
	public String getPathDistences(List<String> path) {
		int totalDistences = 0;
		String returnStr=null;
		if(path==null || path.size()<2) {
			throw new IllegalArgumentException("path at least have 2 node.");
		} else {
			String from = path.get(0);
			for(int index=1;index<path.size();index++) {
				String to = path.get(index);
				if(graph.getMap()[graph.getNodeIndexByName(from)][graph.getNodeIndexByName(to)]>0) {
					totalDistences+=graph.getMap()[graph.getNodeIndexByName(from)][graph.getNodeIndexByName(to)];
					from = to;
				} else {
					totalDistences = GraphDef.NO_PATH;
					returnStr = NO_SUCH_ROUTE;
					break;
				}
			}
			if(returnStr==null) {
				returnStr = totalDistences+"";
			}
		}
		return returnStr;
	}

	public void findWithShortestDistences(List<String> path, int totalDistences, ShortestPathBean shortestPathBean) {
		String lastNodeName=path.get(path.size()-1);
        if (lastNodeName.equals(shortestPathBean.getEndNode()) && totalDistences < shortestPathBean.getShortestPathDistences() && totalDistences > 0) {
        	shortestPathBean.setShortestPath(path);
        	shortestPathBean.setShortestPathDistences(totalDistences);
        	logger.debug("found one:"+shortestPathBean);
            return;
        }

		int[] vectors = graph.getVectorsFor(lastNodeName);
		if(vectors==null) {
			logger.debug("Didn't found node by name:"+lastNodeName);
			return;
		}
		for (int i = 0; i < vectors.length; i++) {
			if (vectors[i] > 0) {
				String nodeName = graph.getNodeNameAt(i);
                if (path.contains(nodeName) && !path.get(0).equals(nodeName))
                    continue;
				List<String> newPath = new ArrayList<String>();
				newPath.addAll(path);
				newPath.add(nodeName);
				int newDistences = totalDistences+vectors[i];
				findWithShortestDistences(newPath, newDistences , shortestPathBean);
			}
		}
	}
	
	public void findPathWithinMaxDistences(QueryTotalBean bean, List<String> path, int totalDistences, int maxDistences) {
		if (totalDistences >= maxDistences)
			return;
		
		if (path.size() > 1 && path.get(path.size()-1).equals(bean.getEnd())) {
			logger.debug("find one path within max distences:");
			logger.debug(path+","+totalDistences);
			bean.addOne();
		}
		
		String lastNodeName=path.get(path.size()-1);
		int[] vectors = graph.getVectorsFor(lastNodeName);
		if(vectors==null) {
			logger.debug("Didn't found node by name:"+lastNodeName);
			return;
		}
		for (int i = 0; i < vectors.length; i++) {
			if (vectors[i] > 0) {
				String nodeName = graph.getNodeNameAt(i);
				List<String> newPath = new ArrayList<String>();
				newPath.addAll(path);
				newPath.add(nodeName);
				int newDistences = totalDistences+vectors[i];
				findPathWithinMaxDistences(bean, newPath, newDistences , maxDistences);
			}
		}
	}
	
	public void findWithMaxStops(QueryTotalBean bean, List<String> path, int maxStops) {
		if (path.size() - 1 > maxStops)
			return;
		
		if (path.size() > 1 && path.get(path.size()-1).equals(bean.getEnd())) {
			logger.debug(path+","+(path.size()-1));
			bean.addOne();
			return;
		}
		
		String lastNodeName=path.get(path.size()-1);
		int[] vectors = graph.getVectorsFor(lastNodeName);
		if(vectors==null) {
			logger.debug("Didn't found node by name:"+lastNodeName);
			return;
		}
		for (int i = 0; i < vectors.length; i++) {
			if (vectors[i] > 0) {
				String nodeName = graph.getNodeNameAt(i);
				List<String> newPath = new ArrayList<String>();
				newPath.addAll(path);
				newPath.add(nodeName);
				findWithMaxStops(bean , newPath, maxStops);
			}
		}
	}
	
	public void findWithExactStops(QueryTotalBean bean, List<String> path, int exactStops) {
		if (path.size() - 1 > exactStops)
			return;
		
		if ((path.size()-1) == exactStops && path.get(path.size()-1).equals(bean.getEnd())) {
			logger.debug("Exact stops:");
			logger.debug(path+","+(path.size()-1));
			bean.addOne();
			return;
		}
		
		String lastNodeName=path.get(path.size()-1);
		int[] vectors = graph.getVectorsFor(lastNodeName);
		if(vectors==null) {
			logger.debug("Didn't found node by name:"+lastNodeName);
			return;
		}
		for (int i = 0; i < vectors.length; i++) {
			if (vectors[i] > 0) {
				String nodeName = graph.getNodeNameAt(i);
				List<String> newPath = new ArrayList<String>();
				newPath.addAll(path);
				newPath.add(nodeName);
				findWithExactStops(bean, newPath, exactStops);
			}
		}
	}
}
