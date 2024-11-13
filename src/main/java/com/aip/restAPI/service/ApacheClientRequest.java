package com.aip.restAPI.service;

import com.aip.restAPI.response.GameResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class ApacheClientRequest implements MakeRequest {

    @Override
    public GameResult makeRequest(String URL) {
        ObjectMapper mapper = new ObjectMapper();
        GameResult gameResult = null;
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(URL);
            gameResult = client.execute(request, httpResponse ->
                    mapper.readValue(httpResponse.getEntity().getContent(), GameResult.class));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return gameResult;
    }
}
