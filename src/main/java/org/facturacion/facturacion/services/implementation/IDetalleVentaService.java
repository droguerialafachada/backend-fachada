package org.facturacion.facturacion.services.implementation;

import lombok.AllArgsConstructor;
import org.facturacion.facturacion.domain.DetalleVenta;
import org.facturacion.facturacion.repositories.DetalleVentaRepository;
import org.facturacion.facturacion.services.specification.DetalleVentaService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class IDetalleVentaService implements DetalleVentaService {

    private final DetalleVentaRepository detalleVentaRepository;

    @Override
    public void save(DetalleVenta detalleVenta) {
        detalleVentaRepository.save(detalleVenta);
    }
}
