package org.facturacion.facturacion.dto.producto;

import org.facturacion.facturacion.domain.Producto;

import java.util.Date;

public record ProductoDTO(
        String codigo,
        String nombre,
        Date fechaCreacion
) {

    public static ProductoDTO fromEntity(Producto producto) {

        return new ProductoDTO(
                producto.getCodigo(),
                producto.getNombre(),
                producto.getFechaCreacion()
        );
    }
}
