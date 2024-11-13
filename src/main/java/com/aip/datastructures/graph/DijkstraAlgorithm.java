package com.aip.datastructures.graph;

import java.util.*;

public class DijkstraAlgorithm {
    public int[] getShortestDistanceToReachAllNodes(List<NodeInfo> nodeInfoList, int srcNode, int numVertex) {
        List<Pair>[] adjList = GraphHelper.createAdjList(nodeInfoList, numVertex);
        int[] minDist = new int[numVertex];
        Queue<Pair> minQ = new PriorityQueue<>(Comparator.comparingInt(p -> p.p2));
        Arrays.fill(minDist, Integer.MAX_VALUE);

        minDist[srcNode] = 0;
        minQ.add(new Pair(srcNode, 0));

        while (!minQ.isEmpty()) {
            Pair pair = minQ.poll();
            int node = pair.p1;
            int wt = pair.p2;

            for (int idx=0; idx<adjList[node].size(); idx++) {
                Pair nextPair = adjList[node].get(idx);
                int nextNode = nextPair.p1;
                int distance = nextPair.p2 + wt;

                if (distance < minDist[nextNode]) {
                    minDist[nextNode] = distance;
                    minQ.add(new Pair(nextNode, distance));
                }
            }
        }

        return minDist;
    }
}
