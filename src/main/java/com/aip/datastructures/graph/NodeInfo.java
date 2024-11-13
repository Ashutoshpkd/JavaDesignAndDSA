package com.aip.datastructures.graph;

public class NodeInfo {
    int e1;
    int e2;
    int w;

    public NodeInfo(int e1, int e2, int w) {
        this.e1 = e1;
        this.e2 = e2;
        this.w = w;
    }

    @Override
    public String toString() {
        return "NodeInfo{" +
                "e1=" + e1 +
                ", e2=" + e2 +
                ", w=" + w +
                '}';
    }
}
