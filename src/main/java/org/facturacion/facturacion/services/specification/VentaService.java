package org.facturacion.facturacion.services.specification;

import org.facturacion.facturacion.dto.factura.VentaItemDTO;
import org.facturacion.facturacion.dto.factura.CrearVentaDTO;
import org.facturacion.facturacion.dto.factura.VentaDTO;

import java.util.List;

public interface VentaService {

    Integer obtenerSiguienteId();

    VentaDTO guardarVenta(CrearVentaDTO facturaDTO);

    List<VentaItemDTO> obtenerVentas();
}
