package com.fisher.graph.finder;

import java.util.List;

import com.fisher.graph.vo.QueryTotalBean;
import com.fisher.graph.vo.ShortestPathBean;

public interface PathFinder {

	public String getPathDistences(List<String> path);

	public void findWithShortestDistences(List<String> path, int totalDistences, ShortestPathBean shortestPathBean);
	
	public void findPathWithinMaxDistences(QueryTotalBean bean, List<String> path, int totalDistences, int maxDistences);
	
	public void findWithMaxStops(QueryTotalBean bean, List<String> path, int maxStops);
	
	public void findWithExactStops(QueryTotalBean bean, List<String> path, int exactStops);
}
