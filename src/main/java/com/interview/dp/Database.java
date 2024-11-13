package com.interview.dp;

public class Database {

    private int count = 2;
    private Database() {}

    public static Database getInstance() {
        return InstanceHelper.instance;
    }

    private static class InstanceHelper {
        public static Database instance = new Database();

    }
}
