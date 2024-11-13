package com.aip.design.generics;

public class Generic<K , V> {
    private V value;
    private K key;

    public Generic(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public <T> void getData(T data) {
        System.out.println("Generic");
    }
}
