package com.aip.restAPI.request;

import com.aip.restAPI.response.Data;
import com.aip.restAPI.response.GameResult;
import com.aip.restAPI.service.ApacheClientRequest;
import com.aip.restAPI.service.CoreJavaClient;
import com.aip.restAPI.service.MakeRequest;
import com.aip.restAPI.service.OkHttpClientRequest;
import java.util.List;

public class GameData {
    private int totalGoals = 0;
    private int totalPages = 10;
    private static final String MATCH_URL = "https://jsonmock.hackerrank.com/api/football_matches";

    private String constructParams(String teamName, String teamNumber, int year, int page) {
        return "?year=" + year + "&" + teamName + "=" + teamNumber + "&page=" + page;
    }

    private void calculateGoalsOnCurrPage(List<Data> gamedata, String teamName) {
        gamedata.stream().forEach(data -> {
            if (teamName.equals("team1")) {
                totalGoals += Integer.parseInt(data.getTeam1goals());
            } else {
                totalGoals += Integer.parseInt(data.getTeam2goals());
            }
        });
    }

    private void makeRequest(String teamName, String team, int year, int currPage) {
        String params = constructParams(teamName, team, year, currPage);
        System.out.println("URL = " + MATCH_URL + params);
        MakeRequest makeRequest = new ApacheClientRequest();
        GameResult gameResult = makeRequest.makeRequest(MATCH_URL + params);
        totalPages = gameResult.getTotal_pages();
        calculateGoalsOnCurrPage(gameResult.getData(), teamName);
    }

    private void getGoals(String teamName, String team, int year) {
        int currPage = 1;
        do {
            makeRequest(teamName, team, year, currPage);
            currPage++;
        } while (currPage <= totalPages);
    }
    public int getTotalGoals(String team, int year) {
        getGoals("team1", team, year);
        getGoals("team2", team, year);

        return totalGoals;
    }
}
