package org.facturacion.facturacion.controllers;

import lombok.AllArgsConstructor;
import org.facturacion.facturacion.dto.factura.FacturaItemDTO;
import org.facturacion.facturacion.dto.factura.CrearFacturaDTO;
import org.facturacion.facturacion.dto.factura.FacturaDTO;
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

    /**
     * Este método se encarga de obtener el siguiente ID de factura disponible.
     * @return Siguiente ID de factura disponible.
     */
    @GetMapping("/siguiente-id")
    public ResponseEntity<Integer> obtenerSiguienteID(){
        return ResponseEntity.ok(this.facturaService.obtenerSiguienteId());
    }

    /**
     * Este método se encarga de guardar una factura en la base de datos.
     * @param facturaDTO Datos de la factura a guardar.
     * @return Factura guardada.
     */
    @PostMapping("/guardar")
    public ResponseEntity<FacturaDTO> guardarFactura(@RequestBody CrearFacturaDTO facturaDTO){
        return ResponseEntity.ok(this.facturaService.guardarFactura(facturaDTO));
    }

    /**
     * Este método se encarga de obtener todas las facturas registradas en la base de datos.
     * @return Lista de facturas registradas.
     */
    @GetMapping("/obtener-facturas")
    public ResponseEntity<List<FacturaItemDTO>> obtenerFacturas(){
        return ResponseEntity.ok(this.facturaService.obtenerFacturas());
    }
}
