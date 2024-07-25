package org.facturacion.facturacion.services.implementation;

import lombok.AllArgsConstructor;
import org.facturacion.facturacion.repositories.FacturaRepository;
import org.facturacion.facturacion.services.specification.FacturaService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class IFacturaService implements FacturaService {

    private final FacturaRepository facturaRepository;

    @Override
    public Integer obtenerSiguienteId() {
        return facturaRepository.obtenerSiguienteId();
    }
}
