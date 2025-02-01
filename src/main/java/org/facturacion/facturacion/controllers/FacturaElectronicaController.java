package org.facturacion.facturacion.controllers;

import lombok.AllArgsConstructor;
import org.facturacion.facturacion.dto.efactura.CrearEFacturaDTO;
import org.facturacion.facturacion.dto.efactura.EFacturaDTO;
import org.facturacion.facturacion.services.specification.FacturaElectronicaService;
import org.springframework.data.domain.Page;
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
    /**
     * Este método se encarga de listar todas las facturas electrónicas registradas en la base de datos.
     * @return Lista de facturas electrónicas registradas.
     */
    @GetMapping("/obtener-efacturas")
    public ResponseEntity<Page<EFacturaDTO>> obtenerFacturaElectronica(@RequestParam(defaultValue = "0") int page,
                                                                             @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(this.facturaElectronicaService.obtenerFacturasElectronica(page, size));
    }
    /**
     * Este método se encarga de obtener una factura electrónica por su id.
     * @param id Id de la factura electrónica a buscar.
     * @return Factura electrónica encontrada.
     */
    @GetMapping("/{id}")
    public ResponseEntity<EFacturaDTO> obtenerFacturaElectronicaPorId(@PathVariable Integer id){
        return ResponseEntity.ok(this.facturaElectronicaService.obtenerFacturaElectronicaPorId(id));
    }
    /**
     * Este método se encarga de guardar una factura electrónica.
     * @param facturaElectronicaDTO Datos de la factura electrónica a guardar.
     * @return Factura electrónica guardada.
     */
    @PostMapping("/guardar")
    public ResponseEntity<EFacturaDTO> guardarFacturaElectronica(@RequestBody CrearEFacturaDTO facturaElectronicaDTO){
        return ResponseEntity.ok(this.facturaElectronicaService.guardarFacturaElectronica(facturaElectronicaDTO));
    }



}
