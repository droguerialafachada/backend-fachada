package org.facturacion.facturacion.services.implementation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class KeepAliveService {

    private final RestTemplate restTemplate;
    private final String selfUrl;

    public KeepAliveService(RestTemplate restTemplate,
                            @Value("${app.self-url}") String selfUrl) {
        this.restTemplate = restTemplate;
        this.selfUrl = selfUrl;
    }

    public void sendKeepAliveRequest() {
        try {
            // Realizamos la petición GET al propio backend
            ResponseEntity<String> response = restTemplate.getForEntity(selfUrl, String.class);
            System.out.println("Respuesta del servidor: " + response.getBody());
        } catch (Exception e) {
            System.err.println("Error al enviar la petición: " + e.getMessage());
        }
    }
}
