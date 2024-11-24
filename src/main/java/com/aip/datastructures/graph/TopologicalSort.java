package com.aip.datastructures.graph;

import com.aip.datastructures.helpers.Helper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class TopologicalSort {

    public int[] topologicalSort(List<NodeInfo> nodeInfoList, int numVertex) {
        return topologicalSortDfs(nodeInfoList, numVertex);
    }

    private int[] topologicalSortDfs(List<NodeInfo> nodeInfoList, int numVertex) {
        List<Pair>[] adjList = Helper.createAdjList(nodeInfoList, numVertex);
        int[] topologicalSort = new int[numVertex];
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> topoSort = new Stack<>();

        for (int node=0; node<numVertex; node++) {
            if (!visited.contains(node)) {
                dfs(adjList, node, visited, topoSort);
            }
        }

        int idx = 0;
        while (!topoSort.isEmpty()) {
            topologicalSort[idx++] = topoSort.pop();
        }

        return topologicalSort;
    }

    private void dfs(List<Pair>[] adjList, int node, Set<Integer> visited, Stack<Integer> topoSort) {
        visited.add(node);

        for (int idx=0; idx<adjList[node].size(); idx++) {
            Pair next = adjList[node].get(idx);

            if (!visited.contains(next.p1)) {
                dfs(adjList, next.p1, visited, topoSort);
            }
        }

        topoSort.push(node);
    }
}
