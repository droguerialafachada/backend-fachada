package org.facturacion.facturacion.services.specification;

import org.facturacion.facturacion.dto.efactura.CrearEFacturaDTO;
import org.facturacion.facturacion.dto.efactura.EFacturaDTO;

import java.util.List;

public interface FacturaElectronicaService {
    List<EFacturaDTO> obtenerFacturasElectronica();

    EFacturaDTO obtenerFacturaElectronicaPorId(Integer id);

    EFacturaDTO guardarFacturaElectronica(CrearEFacturaDTO facturaElectronicaDTO);
}
