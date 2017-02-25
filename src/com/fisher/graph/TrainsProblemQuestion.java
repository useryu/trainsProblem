package com.fisher.graph;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.fisher.graph.finder.DfsPathFinder;
import com.fisher.graph.finder.PathFinder;
import com.fisher.graph.input.GraphDef;
import com.fisher.graph.input.GraphFactory;
import com.fisher.graph.log.Logger;
import com.fisher.graph.output.ExampleOutputer;
import com.fisher.graph.vo.QueryTotalBean;
import com.fisher.graph.vo.ShortestPathBean;

public class TrainsProblemQuestion {
	private static final Logger logger = Logger.getInstance();

	public static void main(String[] args) throws FileNotFoundException, IOException {
		GraphFactory factory = new GraphFactory();
		Properties input = new Properties();
		InputStream in = ClassLoader.getSystemResourceAsStream("input.properties");
		input.load(in);
		GraphDef graphDef = factory.buildGraph(input );
		ExampleOutputer outPuter = new ExampleOutputer(System.out);
		PathFinder finder = new DfsPathFinder(graphDef);
		//1
		List<String> path1 = factory.buildPath("A-B-C");
		outPuter.printResult("1", finder.getPathDistences(path1));
		//2
		List<String> path2 = factory.buildPath("A-D");
		outPuter.printResult("2", finder.getPathDistences(path2));
		//3
		List<String> path3 = factory.buildPath("A-D-C");
		outPuter.printResult("3", finder.getPathDistences(path3));
		//4
		List<String> path4 = factory.buildPath("A-E-B-C-D");
		outPuter.printResult("4", finder.getPathDistences(path4));
		//5
		List<String> path5 = factory.buildPath("A-E-D");
		outPuter.printResult("5", finder.getPathDistences(path5));
		//6
		List<String> path = new ArrayList<String>();
		path.add("C");
		QueryTotalBean bean6 = new QueryTotalBean();
		bean6.setEnd("C");
		finder.findWithMaxStops(bean6 , path , 3);
		outPuter.printResult("6", bean6.getTotal()+"");
		//7
		List<String> path7 = new ArrayList<String>();
		path7.add("A");
		QueryTotalBean bean7 = new QueryTotalBean();
		bean7.setEnd("C");
		finder.findWithExactStops(bean7, path7 , 4);
		outPuter.printResult("7", bean7.getTotal()+"");
		//8
		List<String> path8 = new ArrayList<String>();
		ShortestPathBean bean8 = new ShortestPathBean();
		bean8.setStartNode("A");
		bean8.setEndNode("C");
		path8.add(bean8.getStartNode());
		finder.findWithShortestDistences(path8, 0 , bean8 );
		if(bean8.getShortestPathDistences()==Integer.MAX_VALUE) {
			logger.debug("Do not found path between "+bean8.getStartNode()+" and "+bean8.getEndNode());
		} else {
			logger.debug(bean8.getShortestPath()+","+bean8.getShortestPathDistences());
		}
		outPuter.printResult("8", bean8.getShortestPathDistences()+"");
		//9
		List<String> path9 = new ArrayList<String>();
		ShortestPathBean bean9 = new ShortestPathBean();
		bean9.setStartNode("B");
		bean9.setEndNode("B");
		path9.add(bean9.getStartNode());
		finder.findWithShortestDistences(path9, 0 , bean9);
		if(bean9.getShortestPathDistences()==Integer.MAX_VALUE) {
			logger.debug("Do not found path between "+bean9.getStartNode()+" and "+bean9.getEndNode());
		} else {
			logger.debug(bean9.getShortestPath()+","+bean9.getShortestPathDistences());
		}
		outPuter.printResult("9", bean9.getShortestPathDistences()+"");
		//10
		List<String> path10 = new ArrayList<String>();
		path10.add("C");
		QueryTotalBean bean10 = new QueryTotalBean();
		bean10.setEnd("C");
		finder.findPathWithinMaxDistences(bean10 , path10, 0 , 30);
		outPuter.printResult("10", bean10.getTotal()+"");
	}

}
