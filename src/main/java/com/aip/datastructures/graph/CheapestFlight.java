package com.aip.datastructures.graph;

import java.util.*;

public class CheapestFlight {
    public int findCheapestPrice(int n, List<NodeInfo> flights, int src, int dst, int k) {
        Queue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n2 -> n2.price));
        List<Node>[] adjList = createAdjList(flights, n);
        pq.add(new Node(src, 0, 0));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();

            if (curr.node == dst) return curr.price;

            for (int i=0; i<adjList[curr.node].size(); i++) {
                Node next = adjList[curr.node].get(i);

                if (curr.stops <= k) {
                    pq.add(new Node(next.node, curr.price + next.price, curr.stops + 1));
                }
            }
        }

        return -1;
    }

    private List<Node>[] createAdjList(List<NodeInfo> flights, int n) {
        List<Node>[] adjList = new List[n];

        for (int i=0; i<n; i++) adjList[i] = new ArrayList<>();

        for (NodeInfo flight : flights) {
            int src = flight.e1;
            int to = flight.e2;
            int price = flight.w;

            adjList[src].add(new Node(to, price, 0));
        }

        return adjList;
    }

    private static class Node {
        int node;
        int price;
        int stops;

        public Node(int node, int price, int stops) {
            this.node = node;
            this.price = price;
            this.stops = stops;
        }
    }
}
