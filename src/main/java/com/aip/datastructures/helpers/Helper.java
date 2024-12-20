package com.aip.datastructures.helpers;

import com.aip.datastructures.graph.NodeInfo;
import com.aip.datastructures.graph.Pair;

import java.util.ArrayList;
import java.util.List;

public class Helper {
    public static List<Pair>[] createAdjList(List<NodeInfo> nodeInfoList, int vertex) {
        List<Pair>[] adjList = new List[vertex];
        for (NodeInfo nodeInfo : nodeInfoList) {
            if (adjList[nodeInfo.e1] == null) {
                adjList[nodeInfo.e1] = new ArrayList<>();
            }

            if (adjList[nodeInfo.e2] == null) {
                adjList[nodeInfo.e2] = new ArrayList<>();
            }
            Pair p1 = new Pair(nodeInfo.e1, nodeInfo.w);
            Pair p2 = new Pair(nodeInfo.e2, nodeInfo.w);
            adjList[nodeInfo.e2].add(p1);
            adjList[nodeInfo.e1].add(p2);
        }

        return adjList;
    }

    public static void swap(int[] nums, int i, int pIndex) {
        int temp = nums[i];
        nums[i] = nums[pIndex];
        nums[pIndex] = temp;
    }
}
