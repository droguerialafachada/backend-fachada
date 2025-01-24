package org.facturacion.facturacion.dto.producto;

import org.facturacion.facturacion.domain.Producto;
import org.facturacion.facturacion.dto.formaVenta.FormaVentaDTO;

import java.util.Date;
import java.util.List;

public record FullProductoDTO(
        String codigo,
        String nombre,
        boolean activo,
        Double precioCompra,
        Date fechaCreacion,
        List<FormaVentaDTO> formaVentas
) {

    public static FullProductoDTO fromEntity(Producto producto) {
        return new FullProductoDTO(
                producto.getCodigo(),
                producto.getNombre(),
                producto.getActivo(),
                producto.getPrecioCompra(),
                producto.getFechaCreacion(),
                producto.getFormaVentas().stream().map(FormaVentaDTO::fromEntity).toList()
        );
    }
}
