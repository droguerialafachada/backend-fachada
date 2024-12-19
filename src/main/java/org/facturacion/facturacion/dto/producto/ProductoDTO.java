package org.facturacion.facturacion.dto.producto;

import org.facturacion.facturacion.domain.Producto;

import java.util.Date;

public record ProductoDTO(
        String codigo,
        String nombre,
        Double precio,
        Integer cantidad,
        boolean activo,
        Date fechaCreacion
) {

    public static ProductoDTO fromEntity(Producto producto) {

        return new ProductoDTO(
                producto.getCodigo(),
                producto.getNombre(),
                producto.getPrecio(),
                producto.getStock(),
                producto.getActivo(),
                producto.getFechaCreacion()
        );
    }
}
