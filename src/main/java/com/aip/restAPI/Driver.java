package com.aip.restAPI;

import com.aip.restAPI.request.GameData;

import java.time.LocalDateTime;

public class Driver {
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        GameData gameData = new GameData();
        System.out.println(gameData.getTotalGoals("Barcelona", 2011));
        long endTime = System.nanoTime();
        System.out.println("Time taken - " + (endTime - startTime) / 1000000);
    }

}
