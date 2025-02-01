package org.facturacion.facturacion.services.specification;

import org.facturacion.facturacion.dto.efactura.CrearEFacturaDTO;
import org.facturacion.facturacion.dto.efactura.EFacturaDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FacturaElectronicaService {
    Page<EFacturaDTO> obtenerFacturasElectronica(int page, int size);

    EFacturaDTO obtenerFacturaElectronicaPorId(Integer id);

    EFacturaDTO guardarFacturaElectronica(CrearEFacturaDTO facturaElectronicaDTO);
}
