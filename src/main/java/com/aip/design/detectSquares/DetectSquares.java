package com.aip.design.detectSquares;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class DetectSquares {

    private List<int[]> points;
    private Map<Point, Integer> freq;

    public DetectSquares() {
        this.points = new ArrayList<>();
        this.freq = new HashMap<>();
    }

    public void add(int[] point) {
        points.add(point);
        Point p = Point.getPoint(point[0], point[1]);

        freq.put(p, 1 + freq.getOrDefault(p, 0));
    }

    public int count(int[] point) {
        int res = 0;

        for (int[] p : points) {
            if (Math.abs(p[0] - point[0]) != Math.abs(p[1] - point[1])
            ) continue;

            Point p1 = Point.getPoint(p[0], point[1]);
            Point p2 = Point.getPoint(point[0], p[1]);

            res += freq.getOrDefault(p1, 0) * freq.getOrDefault(p2, 0);
        }

        return res;
    }

    private static class Point {
//        private static Map<String, Point> cache = new HashMap<>();
        private int x;
        private int y;

        // Private constructor to initialize x and y
        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        // Public method to get a Point instance
        public static Point getPoint(int x, int y) {
            return new Point(x, y);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (obj.getClass() != this.getClass()) return false;
            Point other = (Point) obj;
            if (this.x != other.x || this.y != other.y) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int prime = 31;
            int result = 1;
            result = result * prime + ((this.x + "-" + this.y).hashCode());
            return result;
        }
    }

}