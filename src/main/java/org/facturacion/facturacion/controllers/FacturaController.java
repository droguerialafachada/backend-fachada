package org.facturacion.facturacion.controllers;

import lombok.AllArgsConstructor;
import org.facturacion.facturacion.dto.efactura.CrearEFacturaDTO;
import org.facturacion.facturacion.dto.efactura.EFacturaDTO;
import org.facturacion.facturacion.dto.factura.CrearFacturaDTO;
import org.facturacion.facturacion.dto.factura.FacturaDTO;
import org.facturacion.facturacion.services.specification.FacturaElectronicaService;
import org.facturacion.facturacion.services.specification.FacturaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Esta clase es un controlador que expone los servicios relacionados con las facturas
 * de la aplicación. Se encarga de recibir las peticiones HTTP y delegar la lógica de negocio
 * al servicio correspondiente.
 */
@RestController
@RequestMapping("/factura")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class FacturaController {
    private final FacturaService facturaService;

    @GetMapping()
    public ResponseEntity<List<FacturaDTO>> obtenerFacturaElectronica(){
        return ResponseEntity.ok(this.facturaService.obtenerFacturas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacturaDTO> obtenerFacturaElectronicaPorId(@PathVariable Integer id){
        return ResponseEntity.ok(this.facturaService.obtenerFacturaPorId(id));
    }

    @PostMapping("/guardar")
    public ResponseEntity<FacturaDTO> guardarFacturaElectronica(@RequestBody CrearFacturaDTO facturaDTO){
        return ResponseEntity.ok(this.facturaService.guardarFactura(facturaDTO));
    }
}
