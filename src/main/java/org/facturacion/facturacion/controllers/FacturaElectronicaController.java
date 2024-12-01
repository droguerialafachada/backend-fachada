package org.facturacion.facturacion.controllers;

import lombok.AllArgsConstructor;
import org.facturacion.facturacion.dto.efactura.CrearEFacturaDTO;
import org.facturacion.facturacion.dto.efactura.EFacturaDTO;
import org.facturacion.facturacion.services.specification.FacturaElectronicaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Esta clase es un controlador que expone los servicios relacionados con las facturas electrónicas
 * de la aplicación. Se encarga de recibir las peticiones HTTP y delegar la lógica de negocio
 * al servicio correspondiente.
 */
@RestController
@RequestMapping("/efactura")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class FacturaElectronicaController {

    private final FacturaElectronicaService facturaElectronicaService;

    @GetMapping()
    public ResponseEntity<List<EFacturaDTO>> obtenerFacturaElectronica(){
        return ResponseEntity.ok(this.facturaElectronicaService.obtenerFacturasElectronica());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EFacturaDTO> obtenerFacturaElectronicaPorId(@PathVariable Integer id){
        return ResponseEntity.ok(this.facturaElectronicaService.obtenerFacturaElectronicaPorId(id));
    }

    @PostMapping("/guardar")
    public ResponseEntity<EFacturaDTO> guardarFacturaElectronica(@RequestBody CrearEFacturaDTO facturaElectronicaDTO){
        return ResponseEntity.ok(this.facturaElectronicaService.guardarFacturaElectronica(facturaElectronicaDTO));
    }



}
