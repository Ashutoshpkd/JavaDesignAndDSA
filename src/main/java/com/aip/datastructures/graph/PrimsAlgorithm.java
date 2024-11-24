package com.aip.datastructures.graph;

import com.aip.datastructures.helpers.Helper;

import java.util.*;

public class PrimsAlgorithm {

    public void minimumSpanningTree(List<NodeInfo> nodeInfoList, int vertex) {
        List<Pair>[] adjList = Helper.createAdjList(nodeInfoList, vertex);
        System.out.println(Arrays.toString(adjList));

        Queue<Pair> minQ = new PriorityQueue<>(Comparator.comparingInt(p -> p.p2));
        int[] minCost = new int[vertex];
        int[] parent = new int[vertex];
        Set<Integer> visited = new HashSet<>();

        Arrays.fill(parent, -1);
        Arrays.fill(minCost, Integer.MAX_VALUE);

        minQ.add(new Pair(0, 0));
        parent[0] = -1;
        minCost[0] = 0;

        while (!minQ.isEmpty()) {
            Pair p = minQ.poll();
            int node = p.p1;
            if (visited.contains(node)) continue;

            visited.add(node);

            for (int idx=0; idx<adjList[node].size(); idx++) {
                Pair adjNode = adjList[node].get(idx);
                int adjN = adjNode.p1;
                int adjW = adjNode.p2;

                if (!visited.contains(adjN) && minCost[adjN] > adjW) {
                    minCost[adjN] = adjW;
                    minQ.add(new Pair(adjN, adjW));
                    parent[adjN] = node;
                }
            }
        }
        System.out.println(Arrays.toString(parent));
        System.out.println(Arrays.toString(minCost));

    }
}
