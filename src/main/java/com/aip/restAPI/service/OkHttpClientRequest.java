package com.aip.restAPI.service;

import com.aip.restAPI.response.GameResult;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class OkHttpClientRequest implements MakeRequest {

    @Override
    public GameResult makeRequest(String URL) {
        OkHttpClient client = new OkHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();
        GameResult gameResult = null;
        try {
            Request request = new Request.Builder().url(URL).build();
            Response response = client.newCall(request).execute();

            gameResult = objectMapper.readValue(response.body().byteStream(), GameResult.class);
        } catch (StreamReadException e) {
            e.printStackTrace();
        } catch (DatabindException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return gameResult;
        }
    }
}
