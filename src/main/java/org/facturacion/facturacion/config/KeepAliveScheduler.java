package org.facturacion.facturacion.config;

import org.facturacion.facturacion.services.implementation.KeepAliveService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Clase que se encarga de programar la tarea de enviar una petición de keep alive
 * al backend cada 3 segundos. Para mantener activo el contenedor de Render.
 */
@Configuration
@EnableScheduling
public class KeepAliveScheduler {

    private final KeepAliveService keepAliveService;

    public KeepAliveScheduler(KeepAliveService keepAliveService) {
        this.keepAliveService = keepAliveService;
    }

    /**
     * Método que se encarga de enviar una petición de keep alive al backend cada 3 segundos.
     */
    @Scheduled(fixedRate = 3000)
    public void keepBackendAlive() {
        keepAliveService.sendKeepAliveRequest();
    }
}
