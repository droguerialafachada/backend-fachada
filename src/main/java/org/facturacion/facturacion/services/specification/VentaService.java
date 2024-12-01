package org.facturacion.facturacion.services.specification;

import org.facturacion.facturacion.dto.venta.VentaItemDTO;
import org.facturacion.facturacion.dto.venta.CrearVentaDTO;
import org.facturacion.facturacion.dto.venta.VentaDTO;

import java.util.List;

public interface VentaService {

    Integer obtenerSiguienteId();

    VentaDTO guardarVenta(CrearVentaDTO facturaDTO);

    List<VentaItemDTO> obtenerVentas();
}
