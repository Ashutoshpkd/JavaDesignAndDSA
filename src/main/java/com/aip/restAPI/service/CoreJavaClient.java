package com.aip.restAPI.service;

import com.aip.restAPI.response.GameResult;
import com.aip.restAPI.response.JsonBodyHandler;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CoreJavaClient implements MakeRequest {
    @Override
    public GameResult makeRequest(String URL) {
        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI(URL))
                    .header("Content-Type", "application/json")
                    .header("accept", "application/json")
                    .GET()
                    .build();

            HttpResponse<GameResult> response = HttpClient
                    .newBuilder()
                    .build()
                    .send(httpRequest, new JsonBodyHandler<>(GameResult.class));

            return response.body();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
