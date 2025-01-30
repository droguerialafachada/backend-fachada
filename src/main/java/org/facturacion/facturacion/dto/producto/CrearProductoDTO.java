package org.facturacion.facturacion.dto.producto;

import org.facturacion.facturacion.domain.Producto;
import org.facturacion.facturacion.dto.formaVenta.CrearFormaVentaDTO;

import java.util.List;

public record CrearProductoDTO(
        String codigo,
        String nombre,
        String impuesto,
        List<CrearFormaVentaDTO> formasVenta
) {

    public Producto toEntity() {
        Producto producto = new Producto();
        producto.setCodigo(codigo);
        producto.setNombre(nombre.substring(0,1).toUpperCase() + nombre.substring(1).toLowerCase());
        producto.setEliminado(false);
        producto.setFechaCreacion(new java.util.Date());
        producto.setFormaVentas(formasVenta.stream().map(fv -> CrearFormaVentaDTO.toEntity(fv, producto)).toList());
        return producto;
    }
}
