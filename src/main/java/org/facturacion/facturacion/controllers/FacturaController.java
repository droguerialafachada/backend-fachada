package org.facturacion.facturacion.controllers;

import lombok.AllArgsConstructor;
import org.facturacion.facturacion.dto.factura.FacturaItemDTO;
import org.facturacion.facturacion.dto.factura.CrearFacturaDTO;
import org.facturacion.facturacion.dto.factura.FacturaDTO;
import org.facturacion.facturacion.services.specification.FacturaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/factura")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class FacturaController {

    private final FacturaService facturaService;

    @GetMapping("/siguiente-id")
    public ResponseEntity<Integer> obtenerSiguienteID(){
        return ResponseEntity.ok(this.facturaService.obtenerSiguienteId());
    }

    @PostMapping("/guardar")
    public ResponseEntity<FacturaDTO> guardarFactura(@RequestBody CrearFacturaDTO facturaDTO){
        return ResponseEntity.ok(this.facturaService.guardarFactura(facturaDTO));
    }

    @GetMapping("/obtener-facturas")
    public ResponseEntity<List<FacturaItemDTO>> obtenerFacturas(){
        return ResponseEntity.ok(this.facturaService.obtenerFacturas());
    }
}
