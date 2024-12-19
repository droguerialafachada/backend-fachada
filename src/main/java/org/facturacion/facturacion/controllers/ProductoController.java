package org.facturacion.facturacion.controllers;

import lombok.AllArgsConstructor;
import org.facturacion.facturacion.dto.producto.ActualizarProductoDTO;
import org.facturacion.facturacion.dto.producto.CrearProductoDTO;
import org.facturacion.facturacion.dto.producto.ProductoDTO;
import org.facturacion.facturacion.services.specification.ProductoService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
/**
 * Esta clase es un controlador que expone los servicios relacionados con los productos
 * de la aplicación. Se encarga de recibir las peticiones HTTP y delegar la lógica de negocio
 * al servicio correspondiente.
 */
@RestController
@RequestMapping("/productos")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    /**
     * Este método se encarga de listar todos los productos registrados en la base de datos.
     * @return Lista de productos registrados.
     */
    @GetMapping
    public ResponseEntity<Page<ProductoDTO>> listarProducto(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(this.productoService.listarProducto(page, size));
    }

    /**
     * Este método se encarga de verificar si el código de un producto existe en la base de datos.
     * @param codProducto Código del producto a verificar.
     * @return True si el producto existe, false en caso contrario.
     */
    @GetMapping("/verificar-cod-producto/{codProducto}")
    public ResponseEntity<Boolean> verificarCodProducto(@PathVariable String codProducto) {
        return ResponseEntity.ok(this.productoService.verificarExisteElCodProducto(codProducto));
    }
    /**
     * Este método crea un nuevo producto. Dado un objeto CrearProductoDTO, se crea un nuevo producto
     * en la base de datos.
     * @param producto Datos del producto a crear.
     */
    @PostMapping("/guardar")
    public ResponseEntity<ProductoDTO> crearProducto(@RequestBody CrearProductoDTO producto) {
        return ResponseEntity.ok(this.productoService.crearProducto(producto));
    }
    /**
     * Este método se encarga de actualizar un producto. Dado un objeto ActualizarProductoDTO, se actualiza
     * un producto en la base de datos.
     * @param producto Datos del producto a actualizar.
     */
    @PutMapping("/actualizar")
    public ResponseEntity<ProductoDTO> actualizarProducto(@RequestBody ActualizarProductoDTO producto) {
        return ResponseEntity.ok(this.productoService.actualizarProducto(producto));
    }
    /**
     * Este método se encarga de eliminar un producto. Dado un código de producto, se elimina el producto
     * de la base de datos.
     * @param codigo Código del producto a eliminar.
     * @return True si el producto fue eliminado, false en caso contrario.
     */
    @DeleteMapping("/eliminar/{codigo}")
    public ResponseEntity<Boolean> eliminarProducto(@PathVariable String codigo) {
        return ResponseEntity.ok(this.productoService.eliminarProducto(codigo));
    }
    /**
     * Este método se encarga de los tipos de impuestos disponibles.
     * @return Lista de tipos de impuestos disponibles.
     */
    @GetMapping("/tipos-impuestos")
    public ResponseEntity<List<String>> getTiposImpuestos() {
        return ResponseEntity.ok(this.productoService.getTiposImpuestos());
    }
    /**
     * Este método se encarga de verificar si un producto tiene suficiente cantidad en inventario.
     * Para realizar una venta.
     * @param cantidad Cantidad a verificar.
     */
    @GetMapping("/verificar-cantidad/{cantidad}/{id}")
    public ResponseEntity<Boolean> verificarCantidad(@PathVariable Integer cantidad, @PathVariable String id){
        return ResponseEntity.ok(this.productoService.verificarCantidad(cantidad, id));
    }
    /**
     * Este método se encarga de verificar si un producto está activo.
     * @param id Identificador del producto a verificar.
     * @return True si el producto está activo, false en caso contrario.
     */
    @GetMapping("/verificar-activo/{id}")
    public ResponseEntity<Boolean> verificarActivo(@PathVariable String id){
        return ResponseEntity.ok(this.productoService.isActivo(id));
    }

    /**
     * Este método se encarga de verificar si un producto fue eliminado.
     * @param id Identificador del producto a verificar.
     * @return True si el producto fue eliminado, false en caso contrario.
     */
    @GetMapping("/fue-eliminado/{id}")
    public ResponseEntity<Boolean> fueEliminado(@PathVariable String id){
        return ResponseEntity.ok(this.productoService.fueEliminado(id));
    }

    /**
     * Este método se encarga de recuperar un producto eliminado dado su id.
     * @param id Identificador del producto a recuperar.
     */
    @GetMapping("/recuperar-producto/{id}")
    public void recuperarProducto(@PathVariable String id){
        this.productoService.recuperarProducto(id);
    }
}
