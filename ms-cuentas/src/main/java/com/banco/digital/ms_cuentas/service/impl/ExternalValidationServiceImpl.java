package com.banco.digital.ms_cuentas.service.impl;


import com.banco.digital.ms_cuentas.response.ApiResponse;
import com.banco.digital.ms_cuentas.response.RenaperResponse;
import com.banco.digital.ms_cuentas.response.VerazResponse;
import com.banco.digital.ms_cuentas.response.WorldsysResponse;
import com.banco.digital.ms_cuentas.service.ExternalValidationService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class ExternalValidationServiceImpl implements ExternalValidationService {
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final Gson gson = new Gson();

    @Override
    public RenaperResponse getRenaperResponse(String dni) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:3000/service/renaper?dni=" + dni))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            Type renaperResponseType = new TypeToken<ApiResponse<RenaperResponse>>() {}.getType();
            ApiResponse<RenaperResponse> apiResponse = gson.fromJson(response.body(), renaperResponseType);

            if (apiResponse.getResponse() != null && !apiResponse.getResponse().isEmpty()){
                System.out.println("Renaper : " + apiResponse.getResponse().toString());

                return apiResponse.getResponse().get(0);
            }
        }
        return null;
    }

    @Override
    public WorldsysResponse getWorldsysResponse(String dni) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:3000/service/worldsys?dni=" + dni))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            Type worldsysResponseType = new TypeToken<ApiResponse<WorldsysResponse>>() {}.getType();
            ApiResponse<WorldsysResponse> apiResponse = gson.fromJson(response.body(), worldsysResponseType);

            if (apiResponse.getResponse() != null && !apiResponse.getResponse().isEmpty()){
                System.out.println("Worldsys : " + apiResponse.getResponse().toString());

                return apiResponse.getResponse().get(0);
            }
        }
        return null;
    }

    @Override
    public VerazResponse getVerazResponse(String dni) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:3000/service/veraz?dni=" + dni))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            Type verazResponseType = new TypeToken<ApiResponse<VerazResponse>>() {}.getType();
            ApiResponse<VerazResponse> apiResponse = gson.fromJson(response.body(), verazResponseType);

            if (apiResponse.getResponse() != null && !apiResponse.getResponse().isEmpty()){
                System.out.println("Veraz : " + apiResponse.getResponse().toString());

                return apiResponse.getResponse().get(0);
            }
        }
        return null;
    }
}