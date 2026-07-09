package com.aip.datastructures.graph;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        PrimsAlgorithm primsAlgorithm = new PrimsAlgorithm();
        DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm();
        BellmanFord bellmanFord = new BellmanFord();
        TopologicalSort tps = new TopologicalSort();
        Solution sl = new Solution();

        sl.bestTeamScore(new int[] {1,3,5,10,15}, new int[] {1,2,3,4,5});

        List<NodeInfo> nodeInfoList = new ArrayList<>();
        nodeInfoList.add(new NodeInfo(0, 1, 3));
        nodeInfoList.add(new NodeInfo(0, 3,6));
        nodeInfoList.add(new NodeInfo(0, 2,1));
        nodeInfoList.add(new NodeInfo(2, 3,25));
        nodeInfoList.add(new NodeInfo(3, 1,25));
        nodeInfoList.add(new NodeInfo(1, 2,6));

        primsAlgorithm.minimumSpanningTree(nodeInfoList, 5);

        List<NodeInfo> nodeInfoListDijkstra = new ArrayList<>();
        nodeInfoListDijkstra.add(new NodeInfo(1, 3, 2));
        nodeInfoListDijkstra.add(new NodeInfo(1, 2, 9));

        nodeInfoListDijkstra.add(new NodeInfo(1, 0, 5));
        nodeInfoListDijkstra.add(new NodeInfo(0, 2, 8));
        nodeInfoListDijkstra.add(new NodeInfo(1, 3, 2));
        nodeInfoListDijkstra.add(new NodeInfo(2, 3, 6));

        List<NodeInfo> nodeInfoListBellmanFord = new ArrayList<>();
        nodeInfoListBellmanFord.add(new NodeInfo(0, 1, 2));
        nodeInfoListBellmanFord.add(new NodeInfo(0, 2,2));
        nodeInfoListBellmanFord.add(new NodeInfo(1, 2, -1));

        List<NodeInfo> nodeInfoListCheapestFlight = new ArrayList<>();
        nodeInfoListCheapestFlight.add(new NodeInfo(0, 1, 1));
        nodeInfoListCheapestFlight.add(new NodeInfo(0, 2, 5));

        nodeInfoListCheapestFlight.add(new NodeInfo(1, 2, 1));
//        nodeInfoListCheapestFlight.add(new NodeInfo(1, 3, 600));
        nodeInfoListCheapestFlight.add(new NodeInfo(2, 3, 1));

        System.out.println(Arrays
                .toString(dijkstraAlgorithm
                        .getShortestDistanceToReachAllNodes(nodeInfoListDijkstra, 0, 4)));

        System.out.println("Topological sort: " + Arrays.toString(tps.topologicalSort(nodeInfoList, 5)));
        System.out.println("Bellmand Ford shortest path: " + bellmanFord.getShortestPath(nodeInfoListBellmanFord,
                3, 0, 2));

        System.out.println("Cheapest flight path: " + (new CheapestFlight())
                .findCheapestPrice(4, nodeInfoListCheapestFlight, 0, 3, 1));
    }
}
