package org.facturacion.facturacion.services.specification;

import org.facturacion.facturacion.dto.factura.CrearFacturaDTO;
import org.facturacion.facturacion.dto.factura.FacturaDTO;

public interface FacturaService {

    Integer obtenerSiguienteId();

    FacturaDTO guardarFactura(CrearFacturaDTO facturaDTO);
}
