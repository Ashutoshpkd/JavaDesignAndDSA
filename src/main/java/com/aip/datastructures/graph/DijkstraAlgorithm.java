package com.aip.datastructures.graph;

import com.aip.datastructures.helpers.Helper;

import java.util.*;

public class DijkstraAlgorithm {
    public int[] getShortestDistanceToReachAllNodes(List<NodeInfo> nodeInfoList, int srcNode, int numVertex) {
        List<Pair>[] adjList = Helper.createAdjList(nodeInfoList, numVertex);
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

    public int[] getShortestDistanceToReachAllNodesPractice(List<NodeInfo> nodeInfoList, int srcNode, int numVertex) {
        int minD[] = new int[numVertex];
        List<Pair>[] adjList = createAdjList(nodeInfoList, numVertex);
        Queue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.p2));

        Arrays.fill(minD, Integer.MAX_VALUE);

        pq.add(new Pair(srcNode, 0));
        minD[srcNode] = 0;

        while (!pq.isEmpty()) {
            Pair currNodePair = pq.poll();
            int n = currNodePair.p1;
            int dist = currNodePair.p2;

            for (int i=0; i<adjList[n].size(); i++) {
                int nextNode = adjList[n].get(i).p1;
                int w = adjList[n].get(i).p2;

                int totalDist = w + dist;

                if (minD[nextNode] > totalDist) {
                    minD[nextNode] = totalDist;
                    pq.add(new Pair(nextNode, totalDist));
                }
            }
        }

        return minD;
    }

    private List<Pair>[] createAdjList(List<NodeInfo> nodeInfoList, int numVertex) {
        List<Pair>[] adj = new List[numVertex];

        for (int i=0; i<numVertex; i++) adj[i] = new ArrayList<>();

        for (int i=0; i<nodeInfoList.size(); i++) {
            int u = nodeInfoList.get(i).e1;
            int v = nodeInfoList.get(i).e2;
            int w = nodeInfoList.get(i).w;

            adj[u].add(new Pair(v, w));
            adj[v].add(new Pair(u, w));
        }

        return adj;
    }
}
