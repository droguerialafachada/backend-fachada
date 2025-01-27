package org.facturacion.facturacion.dto.formaVenta;

import org.facturacion.facturacion.domain.FormaVenta;

public record FormaVentaDTO(
        Long id,
        String nombre,
        Double precioCompra,
        Integer cantidad,
        Double precioVenta
) {


    public static FormaVentaDTO fromEntity(FormaVenta formaVenta) {
        return new FormaVentaDTO(
                formaVenta.getId(),
                formaVenta.getNombre().toUpperCase(),
                formaVenta.getPrecioCompra(),
                formaVenta.getCantidad(),
                formaVenta.getPrecioVenta()
        );
    }
}
