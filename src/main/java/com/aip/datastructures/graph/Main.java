package com.aip.datastructures.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        PrimsAlgorithm primsAlgorithm = new PrimsAlgorithm();
        DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm();
        TopologicalSort tps = new TopologicalSort();

        List<NodeInfo> nodeInfoList = new ArrayList<>();
        nodeInfoList.add(new NodeInfo(0, 1, 2));
        nodeInfoList.add(new NodeInfo(0, 3,6));
        nodeInfoList.add(new NodeInfo(1, 3,8));
        nodeInfoList.add(new NodeInfo(1, 4,5));
        nodeInfoList.add(new NodeInfo(1, 2,3));
        nodeInfoList.add(new NodeInfo(2, 4,7));

        primsAlgorithm.minimumSpanningTree(nodeInfoList, 5);

        List<NodeInfo> nodeInfoListDijkstra = new ArrayList<>();
        nodeInfoListDijkstra.add(new NodeInfo(1, 3, 2));
        nodeInfoListDijkstra.add(new NodeInfo(1, 2, 9));

        nodeInfoListDijkstra.add(new NodeInfo(1, 0, 5));
        nodeInfoListDijkstra.add(new NodeInfo(0, 2, 8));
        nodeInfoListDijkstra.add(new NodeInfo(1, 3, 2));
        nodeInfoListDijkstra.add(new NodeInfo(2, 3, 6));

        System.out.println(Arrays
                .toString(dijkstraAlgorithm
                        .getShortestDistanceToReachAllNodes(nodeInfoListDijkstra, 0, 4)));

        System.out.println("Topological sort: " + Arrays.toString(tps.topologicalSort(nodeInfoList, 5)));
    }
}
