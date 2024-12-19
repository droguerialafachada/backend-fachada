package org.facturacion.facturacion.services.specification;

import org.facturacion.facturacion.dto.venta.FullVentaDTO;
import org.facturacion.facturacion.dto.venta.VentaItemDTO;
import org.facturacion.facturacion.dto.venta.CrearVentaDTO;
import org.facturacion.facturacion.dto.venta.VentaDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface VentaService {

    Integer obtenerSiguienteId();

    VentaDTO guardarVenta(CrearVentaDTO facturaDTO);

    List<VentaItemDTO> obtenerVentas();

    Boolean cancelarVenta(Integer id);

    Page<VentaDTO> obtenerVentasCompletadas(int page, int size);

    FullVentaDTO obtenerVentaPorId(Integer id);

}
