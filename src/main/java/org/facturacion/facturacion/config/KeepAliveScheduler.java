package org.facturacion.facturacion.config;

import org.facturacion.facturacion.services.implementation.KeepAliveService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class KeepAliveScheduler {

    private final KeepAliveService keepAliveService;

    public KeepAliveScheduler(KeepAliveService keepAliveService) {
        this.keepAliveService = keepAliveService;
    }

    // Programamos la tarea para que se ejecute cada 3 segundos
    @Scheduled(fixedRate = 3000)
    public void keepBackendAlive() {
        keepAliveService.sendKeepAliveRequest();
    }
}
