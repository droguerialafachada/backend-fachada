package org.facturacion.facturacion.controllers;

import lombok.AllArgsConstructor;
import org.facturacion.facturacion.dto.factura.VentaItemDTO;
import org.facturacion.facturacion.dto.factura.CrearVentaDTO;
import org.facturacion.facturacion.dto.factura.VentaDTO;
import org.facturacion.facturacion.services.specification.VentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Esta clase es un controlador que expone los servicios relacionados con las ventas
 * de la aplicación. Se encarga de recibir las peticiones HTTP y delegar la lógica de negocio
 * al servicio correspondiente.
 */
@RestController
@RequestMapping("/venta")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class VentaController {

    private final VentaService ventaService;

    /**
     * Este método se encarga de obtener el siguiente ID de venta disponible.
     * @return Siguiente ID de venta disponible.
     */
    @GetMapping("/siguiente-id")
    public ResponseEntity<Integer> obtenerSiguienteID(){
        return ResponseEntity.ok(this.ventaService.obtenerSiguienteId());
    }

    /**
     * Este método se encarga de guardar una venta en la base de datos.
     * @param ventaDTO Datos de la venta a guardar.
     * @return venta guardada.
     */
    @PostMapping("/guardar")
    public ResponseEntity<VentaDTO> guardarVenta(@RequestBody CrearVentaDTO ventaDTO){
        return ResponseEntity.ok(this.ventaService.guardarVenta(ventaDTO));
    }

    /**
     * Este método se encarga de obtener todas las ventas registradas en la base de datos.
     * @return Lista de ventas registradas.
     */
    @GetMapping("/obtener-ventas")
    public ResponseEntity<List<VentaItemDTO>> obtenerVentas(){
        return ResponseEntity.ok(this.ventaService.obtenerVentas());
    }
}
