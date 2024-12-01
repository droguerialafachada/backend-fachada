package org.facturacion.facturacion.services.implementation;

import lombok.AllArgsConstructor;
import org.facturacion.facturacion.domain.Factura;
import org.facturacion.facturacion.domain.FacturaElectronica;
import org.facturacion.facturacion.domain.Venta;
import org.facturacion.facturacion.dto.efactura.EFacturaDTO;
import org.facturacion.facturacion.dto.factura.CrearFacturaDTO;
import org.facturacion.facturacion.dto.factura.FacturaDTO;
import org.facturacion.facturacion.exceptions.efactura.EFacturaNoExisteException;
import org.facturacion.facturacion.exceptions.factura.FacturaNoExisteException;
import org.facturacion.facturacion.repositories.FacturaRepository;
import org.facturacion.facturacion.repositories.VentaRepository;
import org.facturacion.facturacion.services.specification.FacturaService;
import org.springframework.stereotype.Service;
import java.util.List;


@AllArgsConstructor
@Service
public class IFacturaService implements FacturaService {

    private final FacturaRepository facturaRepository;
    private final VentaRepository ventaRepository;

    @Override
    public List<FacturaDTO> obtenerFacturas() {
        return facturaRepository
                .findAll()
                .stream()
                .map(FacturaDTO::fromEntity)
                .toList();
    }

    @Override
    public FacturaDTO obtenerFacturaPorId(Integer id) {
        return facturaRepository
                .findById(id)
                .map(FacturaDTO::fromEntity)
                .orElse(null);
    }

    @Override
    public FacturaDTO guardarFactura(CrearFacturaDTO facturaDTO) {
        Venta venta = ventaRepository.findById(facturaDTO.idVenta()).orElse(null);
        if(venta == null) throw new FacturaNoExisteException("La venta no existe");
        Factura facturaElectronica = new Factura();
        facturaElectronica.setCliente(venta.getCliente());
        facturaElectronica.setUsuario(venta.getUsuario());
        facturaElectronica.setDetalleVentaList(venta.getDetalleVentaList());
        facturaElectronica.setTotal(venta.getTotal());
        //La fecha de la factura es la fecha de creación no la fecha de la venta
        facturaElectronica.setFecha(new java.util.Date());

        return FacturaDTO.fromEntity(facturaRepository.save(facturaElectronica));
    }
}
