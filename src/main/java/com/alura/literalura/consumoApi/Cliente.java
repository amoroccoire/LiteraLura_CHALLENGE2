package com.alura.literalura.consumoApi;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.net.http.HttpClient.newHttpClient;

@Component
public class Cliente {
    private final HttpClient client;

    public Cliente() {
        this.client = HttpClient.newHttpClient();
    }

    public String getData(String path) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(path))
                .build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        String json = response.body();
        System.out.println("RESPUESTA " + json.length() + " CARACTERES");
        return json;
    }
}
