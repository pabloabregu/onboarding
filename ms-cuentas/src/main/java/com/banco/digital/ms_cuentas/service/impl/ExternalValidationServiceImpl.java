package com.banco.digital.ms_cuentas.service.impl;


import com.banco.digital.ms_cuentas.model.ExternalValidationResult;
import com.banco.digital.ms_cuentas.response.*;
import com.banco.digital.ms_cuentas.service.ExternalValidationService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger logger = LoggerFactory.getLogger(ExternalValidationServiceImpl.class);
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final Gson gson = new Gson();

    @Override
    public ExternalValidationResult getValidationResults(String dni) throws URISyntaxException, IOException, InterruptedException {
        boolean renaper = getRenaperResponse(dni).isAuthorize();
        boolean worldSys = getWorldsysResponse(dni).isTerrorist();
        double veraz = getVerazResponse(dni).getScore();

        return new ExternalValidationResult(renaper, worldSys, veraz);
    }

    @Override
    public RenaperResponse getRenaperResponse(String dni) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:3000/service/renaper?dni=" + dni))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            Type renaperResponseType = new TypeToken<ApiResponse<RenaperResponse>>() {
            }.getType();
            ApiResponse<RenaperResponse> apiResponse = gson.fromJson(response.body(), renaperResponseType);

            if (apiResponse.getResponse() != null && !apiResponse.getResponse().isEmpty()) {
                logger.info("Renaper : {}", apiResponse.getResponse());

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
            Type worldsysResponseType = new TypeToken<ApiResponse<WorldsysResponse>>() {
            }.getType();
            ApiResponse<WorldsysResponse> apiResponse = gson.fromJson(response.body(), worldsysResponseType);

            if (apiResponse.getResponse() != null && !apiResponse.getResponse().isEmpty()) {
                logger.info("Worldsys : {}", apiResponse.getResponse());

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
            Type verazResponseType = new TypeToken<ApiResponse<VerazResponse>>() {
            }.getType();
            ApiResponse<VerazResponse> apiResponse = gson.fromJson(response.body(), verazResponseType);

            if (apiResponse.getResponse() != null && !apiResponse.getResponse().isEmpty()) {
                logger.info("Veraz : {}", apiResponse.getResponse());

                return apiResponse.getResponse().get(0);
            }
        }
        return null;
    }
}
