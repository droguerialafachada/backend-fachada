package org.facturacion.facturacion.services.implementation;

import lombok.AllArgsConstructor;
import org.facturacion.facturacion.domain.EstadoVenta;
import org.facturacion.facturacion.domain.Factura;
import org.facturacion.facturacion.domain.Venta;
import org.facturacion.facturacion.dto.factura.CrearFacturaDTO;
import org.facturacion.facturacion.dto.factura.FacturaDTO;
import org.facturacion.facturacion.exceptions.efactura.VentaCanceladaException;
import org.facturacion.facturacion.exceptions.factura.FacturaNoExisteException;
import org.facturacion.facturacion.repositories.FacturaRepository;
import org.facturacion.facturacion.repositories.VentaRepository;
import org.facturacion.facturacion.services.specification.FacturaService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

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

        if(facturaRepository.existsById(venta.getId())){
            Optional<Factura> factura = facturaRepository.findById(venta.getId());
            if(factura.isPresent()) return FacturaDTO.fromEntity(factura.get());
        }

        if(venta.getEstado().equals(EstadoVenta.CANCELADA))
            throw new VentaCanceladaException("La venta esta cancelada");
        Factura factura = FacturaDTO.toEntity();
        venta.setFactura(factura);
        factura.setVenta(venta);

        return FacturaDTO.fromEntity(facturaRepository.save(factura));
    }
}
