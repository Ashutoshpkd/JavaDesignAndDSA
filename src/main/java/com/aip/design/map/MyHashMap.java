package com.aip.design.map;

import java.util.ArrayList;
import java.util.List;

public class MyHashMap {
    private List[] dataStore = new List[100];

    public MyHashMap() {
        for (int i=0; i<100; i++) {
            this.dataStore[i] = new ArrayList<>();
        }
    }

    public void put(int key, int value) {
        int index = createHash(key);
        Pair p = new Pair(key, value);

        if (this.dataStore[index].size() == 0) {
            this.dataStore[index].add(p);
        } else {
            boolean isPresent = false;

            for (int i=0; i<dataStore[index].size(); i++) {
                Pair foundPair = (Pair) dataStore[index].get(i);
                if (foundPair.key == key) {
                    foundPair.value = value;
                    isPresent = true;
                    break;
                }
            }

            if (!isPresent) {
                this.dataStore[index].add(p);
            }
        }
    }

    public int get(int key) {
        return getValueFromDataStore(key, -1);
    }

    public boolean remove(int key) {
        int index = createHash(key);
        boolean removed = false;

        for (int i=0; i<this.dataStore[index].size(); i++) {
            Pair foundPair = (Pair) this.dataStore[index].get(i);
            if (foundPair.key == key) {
                this.dataStore[index].remove(foundPair);
                removed = true;
                break;
            }
        }

        return removed;
    }

    public int getOrDefault(int key, int defaultValue) {
        return getValueFromDataStore(key, defaultValue);
    }

    private int getValueFromDataStore(int key, int value) {
        int index = createHash(key);

        for (int i=0; i<this.dataStore[index].size(); i++) {
            if (((Pair) this.dataStore[index].get(i)).key == key) {
                value = ((Pair) this.dataStore[index].get(i)).value;
                break;
            }
        }

        return value;
    }

    private int createHash(int key) {
        int hash = 17;
        String keyStr = Integer.toString(key);

        for (int i=0; i<keyStr.length(); i++) {
            hash = (13 * hash * Character.getNumericValue(keyStr.charAt(i))) % 100;
        }

        return hash;
    }

    private class Pair {
        int key;
        int value;

        public Pair(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
