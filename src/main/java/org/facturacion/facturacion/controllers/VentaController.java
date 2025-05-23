package org.facturacion.facturacion.controllers;

import lombok.AllArgsConstructor;
import org.facturacion.facturacion.dto.venta.FullVentaDTO;
import org.facturacion.facturacion.dto.venta.VentaItemDTO;
import org.facturacion.facturacion.dto.venta.CrearVentaDTO;
import org.facturacion.facturacion.dto.venta.VentaDTO;
import org.facturacion.facturacion.services.specification.VentaService;
import org.springframework.data.domain.Page;
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

    /**
     * Este método se encarga de cancelar una venta por su ID.
     * Cancelar una venta implica cambiar su estado a CANCELADA.
     * @param id ID de la venta a obtener.
     * @return Un booleano que indica si la venta fue cancelada.
     */
    @DeleteMapping("/cancelar/{id}")
    public ResponseEntity<Boolean> cancelarVenta(@PathVariable Integer id){
        return ResponseEntity.ok(this.ventaService.cancelarVenta(id));
    }

    /**
     * Este método se encarga de obtener las ventas completadas.
     * @return Lista de ventas completadas.
     */
    @GetMapping("/obtener-ventas-completadas")
    public ResponseEntity<Page<VentaDTO>> obtenerVentasCompletadas(@RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(this.ventaService.obtenerVentasCompletadas(page, size));
    }

    /**
     * Este método se encarga de obtener una venta por su ID.
     * @param id ID de la venta a obtener.
     * @return Venta obtenida.
     */
    @GetMapping("/{id}")
    public ResponseEntity<FullVentaDTO> obtenerVentaPorId(@PathVariable Integer id){
        return ResponseEntity.ok(this.ventaService.obtenerVentaPorId(id));
    }
}
