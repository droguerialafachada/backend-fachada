package org.facturacion.facturacion.controllers;

import lombok.AllArgsConstructor;
import org.facturacion.facturacion.domain.Producto;
import org.facturacion.facturacion.dto.detalleFactura.DetFacturaDTO;
import org.facturacion.facturacion.dto.producto.ActualizarProductoDTO;
import org.facturacion.facturacion.dto.producto.CrearProductoDTO;
import org.facturacion.facturacion.dto.producto.ProductoDTO;
import org.facturacion.facturacion.services.specification.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/productos")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> listarProducto() {
        return ResponseEntity.ok(this.productoService.listarProducto());
    }

    @GetMapping("/{id}")
    public void obtenerProductoPorId(@PathVariable Integer id) {

    }

    @GetMapping("/verificar-cod-producto/{cod_producto}")
    public void verificarSiExiteElCodProducto(@PathVariable String cod_producto) {

    }

    @PostMapping("/disminuir-stock")
    public void disminuirStock(@RequestBody List<DetFacturaDTO> detallesFacturaDTO) {

    }

    @PostMapping("/guardar")
    public ResponseEntity<ProductoDTO> crearProducto(@RequestBody CrearProductoDTO producto) {
        return ResponseEntity.ok(this.productoService.crearProducto(producto));
    }

    @PutMapping("/actualizar")
    public ResponseEntity<ProductoDTO> actualizarProducto(@RequestBody ActualizarProductoDTO producto) {
        System.out.println("producto = " + producto);
        return ResponseEntity.ok(this.productoService.actualizarProducto(producto));
    }

    @DeleteMapping("/eliminar/{codigo}")
    public ResponseEntity<Boolean> eliminarProducto(@PathVariable String codigo) {
        return ResponseEntity.ok(this.productoService.eliminarProducto(codigo));
    }


}
