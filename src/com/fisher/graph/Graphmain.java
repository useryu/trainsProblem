package com.fisher.graph;


public class Graphmain {
	public int[][] map = { { -1, 5, -1, 5, 7 }, { -1, -1, 4, -1, -1 }, { -1, -1, -1, 8, 2 }, { -1, -1, 8, -1, 6 },
			{ -1, 3, -1, -1, -1 }, };


    public void dfs(String end, String path, int cost) {
        if (path.endsWith(end) && cost < bestCost && cost > 0) {
            bestPath = path;
            bestCost = cost;
            return;
        }
        char lastChar = path.charAt(path.length() - 1);
        int lastNodeIndex = lastChar - 'A';
        for (int i = 0; i < map[lastNodeIndex].length; i++) {
            char newChar = (char) (i + 'A');
            int newCost = map[lastNodeIndex][i];
            if (newCost > 0) {
                if (path.indexOf(newChar) > 0)
                    continue;
                dfs(end, path + newChar, cost + newCost);
            }
        }
    }
    public String bestPath = "";
    public int bestCost = Integer.MAX_VALUE;
    public static void main(String[] args) {
        Graphmain g = new Graphmain();
        g.dfs("C", "A", 0); // 8 
//      g.dfs("B", "B", 0); // 9
        System.out.println("Best Path: " + g.bestPath + "/nCost: " + g.bestCost);
    }
}
