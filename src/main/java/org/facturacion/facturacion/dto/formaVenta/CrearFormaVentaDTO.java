package org.facturacion.facturacion.dto.formaVenta;

import org.facturacion.facturacion.domain.FormaVenta;
import org.facturacion.facturacion.domain.Producto;

public record CrearFormaVentaDTO(
        String nombre,
        Double precioCompra,
        Double precioVenta,
        Integer cantidad
) {

    public static CrearFormaVentaDTO fromEntity(FormaVenta formaVenta) {
        return new CrearFormaVentaDTO(
                formaVenta.getNombre(),
                formaVenta.getPrecioCompra(),
                formaVenta.getPrecioVenta(),
                formaVenta.getCantidad()
        );
    }

    public static FormaVenta toEntity(CrearFormaVentaDTO formaVentaDTO, Producto producto) {
        FormaVenta formaVenta = new FormaVenta();
        formaVenta.setNombre(formaVentaDTO.nombre());
        formaVenta.setPrecioCompra(formaVentaDTO.precioCompra());
        formaVenta.setPrecioVenta(formaVentaDTO.precioVenta());
        formaVenta.setCantidad(formaVentaDTO.cantidad());
        formaVenta.setEliminado(false);
        formaVenta.setProducto(producto);
        return formaVenta;
    }
}
