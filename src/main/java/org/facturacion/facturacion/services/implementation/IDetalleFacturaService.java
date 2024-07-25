package org.facturacion.facturacion.services.implementation;

import lombok.AllArgsConstructor;
import org.facturacion.facturacion.domain.DetalleFactura;
import org.facturacion.facturacion.repositories.DetalleFacturaRepository;
import org.facturacion.facturacion.services.specification.DetalleFacturaService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class IDetalleFacturaService implements DetalleFacturaService {

    private final DetalleFacturaRepository detalleFacturaRepository;

    @Override
    public void save(DetalleFactura detalleFactura) {
        detalleFacturaRepository.save(detalleFactura);
    }
}
