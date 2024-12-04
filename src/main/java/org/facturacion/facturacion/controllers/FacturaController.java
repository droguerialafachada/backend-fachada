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

    /**
     * Este método se encarga de listar todas las facturas registradas en la base de datos.
     * @return Lista de facturas registradas.
     */
    @GetMapping()
    public ResponseEntity<List<FacturaDTO>> obtenerFactura(){
        return ResponseEntity.ok(this.facturaService.obtenerFacturas());
    }
    /**
     * Este método se encarga de obtener una factura por su id.
     * @param id Id de la factura a buscar.
     * @return Factura encontrada.
     */
    @GetMapping("/{id}")
    public ResponseEntity<FacturaDTO> obtenerFacturaPorId(@PathVariable Integer id){
        return ResponseEntity.ok(this.facturaService.obtenerFacturaPorId(id));
    }
    /**
     * Este método se encarga de guardar una factura.
     * @param facturaDTO Datos de la factura a guardar.
     * @return Factura guardada.
     */
    @PostMapping("/guardar")
    public ResponseEntity<FacturaDTO> guardarFactura(@RequestBody CrearFacturaDTO facturaDTO){
        return ResponseEntity.ok(this.facturaService.guardarFactura(facturaDTO));
    }
}
