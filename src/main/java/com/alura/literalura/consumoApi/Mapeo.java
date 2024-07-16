package com.alura.literalura.consumoApi;

import com.alura.literalura.model.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Mapeo {
    private ObjectMapper objectMapper;
    public Mapeo(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public ApiResponse deserializar(String json) {
        try {
            System.out.print("RESPUESTA" + json);
            return objectMapper.readValue(json, ApiResponse.class);
        } catch (IOException e) {
            throw new RuntimeException("Error deserializando JSON");
        }
    }
}
