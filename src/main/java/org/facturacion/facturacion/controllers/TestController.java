package org.facturacion.facturacion.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;

/**
 * Esta clase es un controlador que expone un servicio de prueba para verificar
 * que el backend está activo.
 */
@RestController
@RequestMapping("/test")
@CrossOrigin(origins = "*")
public class TestController {

    /**
     * Este método se encarga de verificar que el backend está activo.
     * @return Mensaje de éxito.
     */
    @GetMapping()
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Backend activo en "+ LocalDate.now());
    }
}
