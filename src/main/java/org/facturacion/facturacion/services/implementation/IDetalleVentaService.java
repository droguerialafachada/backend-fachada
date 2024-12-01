package org.facturacion.facturacion.services.implementation;

import lombok.AllArgsConstructor;
import org.facturacion.facturacion.domain.DetalleVenta;
import org.facturacion.facturacion.repositories.DetalleVentaRepository;
import org.facturacion.facturacion.services.specification.DetalleVentaService;
import org.springframework.stereotype.Service;

/**
 * DetalleVentaService implementation
 */
@Service
@AllArgsConstructor
public class IDetalleVentaService implements DetalleVentaService {

    private final DetalleVentaRepository detalleVentaRepository;

    /**
     * Este metodo guarda un detalle de venta
     * @param detalleVenta Detalle de venta a guardar
     */
    @Override
    public void save(DetalleVenta detalleVenta) {
        detalleVentaRepository.save(detalleVenta);
    }
}
