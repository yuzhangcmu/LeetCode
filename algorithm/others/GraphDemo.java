package Algorithms.algorithm.others;

import java.util.ArrayList;

public class GraphDemo {
    private static int M = Integer.MAX_VALUE; 
    
    // http://www.geeksforgeeks.org/greedy-algorithms-set-6-dijkstras-shortest-path-algorithm/
    public static void main(String[] args) {
        int[][] w = {  
                {0, 3, 2000, 7, M},
                {3, 0, 4, 2, M},
                {M, 4, 0, 5, 4},
                {7, 2, 5, 0, 6},
                {M, M , 4, 6, 0}
        };
        
        int[][] w2 = { 
                {0, 10, M, 30, 100},
                {M, 0, 50, M, M},    
                {M, M, 0, M, 10},    
                {M, M, 20, 0, 60},    
                {M, M, M, M, 0}
        };
        
        int start = 1;
        
        int[] shortPath = dijkstra(w2, start);
        
        for (int i = 0; i < shortPath.length; i++) {
            System.out.println("The shortest path length from start to " + i + " is:" + shortPath[i]);
        }
    }
    
    public static int[] dijkstra(int[][] graph, int src) {
        if (graph == null || graph.length == 0 || graph[0].length == 0) {
            return null;
        }
        
        // get to know the number of the vertexs.
        int v = graph.length;
        
        // We need a indicator to know if we have visited the vertex.
        boolean visit[] = new boolean[v];
        
        // record the length result.
        int[] pathLen = new int[v];
                
        // record the path.
        ArrayList<ArrayList<Integer>> path = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < v; i++) {
            path.add(new ArrayList<Integer>());
            path.get(i).add(0);
            path.get(i).add(i);
        }
        
        // setup the source vertex;
        visit[src] = true;
        pathLen[src] = 0;
        
        // stop when all the vertices has been added into the result set.
        for (int i = 0; i < v - 1; i++) {
            
            int minLen = M;
            int minIndex = -1;
            for (int j = 0; j < v; j++) {
                // sometimes there is no route, so just let equal also return.
                // so we use graph[src][j] <= minLen
                if (!visit[j] && graph[src][j] <= minLen) {
                    minLen = graph[src][j];
                    minIndex = j;
                }
            }
            
            // get the new shortest path, add it into the solution set.
            visit[minIndex] = true;
            pathLen[minIndex] = graph[src][minIndex];
            
            // update all the neighbors of the new vertex.
            for (int k = 0; k < v; k++) {
                // if the path which pass the minIndex is shorter than the former path, 
                // just update it.
                if (!visit[k]  
                        && graph[src][minIndex] != M
                        && graph[minIndex][k] != M
                        && graph[src][minIndex] + graph[minIndex][k] < graph[src][k]) {
                    graph[src][k] = graph[src][minIndex] + graph[minIndex][k];
                    
                    path.set(k, new ArrayList<Integer>(path.get(minIndex)));
                    path.get(k).add(k);
                }
            }
        }
        
        for (ArrayList<Integer> array: path) {
            System.out.println(array.toString());
        }
        
        return pathLen;
    }

}
