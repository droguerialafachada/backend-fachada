package org.facturacion.facturacion.dto.producto;

import org.facturacion.facturacion.domain.Producto;

import java.util.Date;

public record ProductoDTO(
        String codigo,
        String nombre,
        String lote,
        Date fechaCreacion,
        Date fechaVencimiento
) {

    public static ProductoDTO fromEntity(Producto producto) {

        return new ProductoDTO(
                producto.getCodigo(),
                producto.getNombre(),
                producto.getLote(),
                producto.getFechaCreacion(),
                producto.getFechaVencimiento()
        );
    }
}
