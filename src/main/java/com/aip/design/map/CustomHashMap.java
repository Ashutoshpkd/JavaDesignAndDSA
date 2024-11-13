package com.aip.design.map;
import java.util.LinkedList;

public class CustomHashMap<K, V> {
    private static final int INITIAL_CAPACITY = 16;
    private LinkedList<Entry<K, V>>[] table;
    private int size;

    public CustomHashMap() {
        table = new LinkedList[INITIAL_CAPACITY];
        size = 0;
    }

    public void put(K key, V value) {
        int index = getIndex(key);
        if (table[index] == null) {
            table[index] = new LinkedList<>();
        }

        for (Entry<K, V> entry : table[index]) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }

        table[index].add(new Entry<>(key, value));
        size++;

        // Resize if the load factor exceeds a certain threshold (e.g., 0.75)
        if ((double) size / table.length > 0.75) {
            resize();
        }
    }

    public V get(K key) {
        int index = getIndex(key);
        LinkedList<Entry<K, V>> bucket = table[index];
        if (bucket != null) {
            for (Entry<K, V> entry : bucket) {
                if (entry.key.equals(key)) {
                    return entry.value;
                }
            }
        }
        return null; // Key not found
    }

    public boolean containsKey(K key) {
        int index = getIndex(key);
        LinkedList<Entry<K, V>> bucket = table[index];
        if (bucket != null) {
            for (Entry<K, V> entry : bucket) {
                if (entry.key.equals(key)) {
                    return true;
                }
            }
        }
        return false; // Key not found
    }

    public int size() {
        return size;
    }

    private int getIndex(K key) {
        if (key == null) {
            return 0;
        }
        return key.hashCode() % table.length;
    }

    private void resize() {
        LinkedList<Entry<K, V>>[] oldTable = table;
        table = new LinkedList[table.length * 2];
        size = 0;

        for (LinkedList<Entry<K, V>> bucket : oldTable) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    put(entry.key, entry.value);
                }
            }
        }
    }

    private static class Entry<K, V> {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        CustomHashMap<String, Integer> map = new CustomHashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        System.out.println("Size: " + map.size());
        System.out.println("Get 'one': " + map.get("one"));
        System.out.println("Contains key 'two': " + map.containsKey("two"));
        System.out.println("Contains key 'four': " + map.containsKey("four"));
    }
}

