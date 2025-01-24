package org.facturacion.facturacion.dto.formaVenta;

import org.facturacion.facturacion.domain.FormaVenta;

public record FormaVentaDTO(
        Long id,
        String nombre,
        Double precio,
        Integer cantidad
) {


    public static FormaVentaDTO fromEntity(FormaVenta formaVenta) {
        return new FormaVentaDTO(
                formaVenta.getId(),
                formaVenta.getNombre(),
                formaVenta.getPrecio(),
                formaVenta.getCantidad()
        );
    }
}
