package com.interview.graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main{
    static class Node {
        String name;
        Map<String, Node> children;

        public Node() {
            this.children = new HashMap<>();
        }

    }

    static Node root = new Node();

    public static void insert(String str) {
        String[] splits = str.split("\\.");
        System.out.println(Arrays.toString(splits));
        Node curr = root;

        for (int i=0; i < splits.length; i++) {
            if (!curr.children.containsKey(splits[i])) {
                Node node = new Node();
                curr.children.put(splits[i], node);
            }

            curr = curr.children.get(splits[i]);
        }
    }

    public static void helper(String[] strs, int n) {
        for (String str : strs) {
            insert(str);
        }
        dfs(root, 0);
    }

    private static void dfs(Node node, int tab) {
        for (String neighbor : node.children.keySet()) {
            for (int i=0; i < tab; i++) {
                System.out.print("\t");
            }
            System.out.print("- " + neighbor + "\n");

            dfs(node.children.get(neighbor), tab + 1);
        }

    }

    public static void main(String[] args) {

        String[] strs = {
                "java.util.vector",
                "java.util.Date",
                "org.json.JSONObject",
                "java.util.regex.Pattern",
                "org.writequit.Strings",
                "java.util.array.concurrent.Arrays"
        };

        helper(strs, strs.length);
    }
}
