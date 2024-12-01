package org.facturacion.facturacion.services.specification;

import org.facturacion.facturacion.dto.factura.CrearFacturaDTO;
import org.facturacion.facturacion.dto.factura.FacturaDTO;

import java.util.List;

public interface FacturaService {
    List<FacturaDTO> obtenerFacturas();

    FacturaDTO obtenerFacturaPorId(Integer id);

    FacturaDTO guardarFactura(CrearFacturaDTO facturaDTO);
}
