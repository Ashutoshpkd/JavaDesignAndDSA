package com.aip.lld.elevator;

import java.util.*;

public class Demo {
    public static void main(String[] args) {
        String[] strings = new String[5];
        List<String> stringList = new ArrayList<>(List.of(strings));
        Set<String> stringSet = new HashSet<>(Arrays.stream(strings).toList());
        NavigableSet<Integer> minH = new TreeSet<>(Integer::compare);
        NavigableSet<Integer> maxH = new TreeSet<>((a, b) -> Integer.compare(b, a));

        List<Integer> list = new ArrayList<>();

        list.add(1);
        list.add(2);

        System.out.println(list.subList(0, 8));

    }
}
