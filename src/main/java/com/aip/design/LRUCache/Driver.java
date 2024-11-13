package com.aip.design.LRUCache;

import java.util.LinkedList;

public class Driver {
    public static void main(String[] args) {
        LinkedList<Integer> ll = new LinkedList<>();
        LRUCache lruCache = new LRUCache(10);
        System.out.println(lruCache.sayHello());
    }
}
