package org.facturacion.facturacion.services.specification;

import org.facturacion.facturacion.dto.factura.FacturaItemDTO;
import org.facturacion.facturacion.dto.factura.CrearFacturaDTO;
import org.facturacion.facturacion.dto.factura.FacturaDTO;

import java.util.List;

public interface FacturaService {

    Integer obtenerSiguienteId();

    FacturaDTO guardarFactura(CrearFacturaDTO facturaDTO);

    List<FacturaItemDTO> obtenerFacturas();
}
