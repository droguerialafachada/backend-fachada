package org.facturacion.facturacion.controllers;

import lombok.AllArgsConstructor;
import org.facturacion.facturacion.services.specification.FacturaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/factura")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class FacturaController {

    private final FacturaService facturaService;

    @GetMapping("/siguiente-id")
    public ResponseEntity<Integer> obtenerSiguienteID(){
        return ResponseEntity.ok(facturaService.obtenerSiguienteId());
    }

}
