package org.facturacion.facturacion.services.implementation;

import lombok.AllArgsConstructor;
import org.facturacion.facturacion.domain.Factura;
import org.facturacion.facturacion.domain.Venta;
import org.facturacion.facturacion.dto.factura.CrearFacturaDTO;
import org.facturacion.facturacion.dto.factura.FacturaDTO;
import org.facturacion.facturacion.exceptions.factura.FacturaNoExisteException;
import org.facturacion.facturacion.repositories.FacturaRepository;
import org.facturacion.facturacion.repositories.VentaRepository;
import org.facturacion.facturacion.services.specification.FacturaService;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * FacturaService implementation
 */
@AllArgsConstructor
@Service
public class IFacturaService implements FacturaService {

    private final FacturaRepository facturaRepository;
    private final VentaRepository ventaRepository;

    /**
     * Este metodo obtiene todas las facturas
     * @return List<FacturaDTO> Lista de facturas
     */
    @Override
    public List<FacturaDTO> obtenerFacturas() {
        return facturaRepository
                .findAll()
                .stream()
                .map(FacturaDTO::fromEntity)
                .toList();
    }

    /**
     * Este metodo obtiene una factura por su id
     * @param id Id de la factura
     * @return FacturaDTO Factura
     */
    @Override
    public FacturaDTO obtenerFacturaPorId(Integer id) {
        return facturaRepository
                .findById(id)
                .map(FacturaDTO::fromEntity)
                .orElse(null);
    }
    /**
     * Este metodo guarda una factura
     * @param facturaDTO Factura a guardar
     * @return FacturaDTO Factura guardada
     */
    @Override
    public FacturaDTO guardarFactura(CrearFacturaDTO facturaDTO) {
        Venta venta = ventaRepository.findById(facturaDTO.idVenta()).orElse(null);
        if(venta == null) throw new FacturaNoExisteException("La venta no existe");
        //TODO: Validar que la venta no tenga una factura asociada
        //TODO: Validar que la venta no este cancelada
        //TODO: Crear un metodo en la entidad Venta que retorne la factura asociada
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
