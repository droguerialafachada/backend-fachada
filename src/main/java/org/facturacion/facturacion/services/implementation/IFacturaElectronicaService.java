package org.facturacion.facturacion.services.implementation;

import lombok.AllArgsConstructor;
import org.facturacion.facturacion.domain.EstadoVenta;
import org.facturacion.facturacion.domain.FacturaElectronica;
import org.facturacion.facturacion.domain.Venta;
import org.facturacion.facturacion.dto.efactura.CrearEFacturaDTO;
import org.facturacion.facturacion.dto.efactura.EFacturaDTO;
import org.facturacion.facturacion.exceptions.efactura.EFacturaNoExisteException;
import org.facturacion.facturacion.exceptions.efactura.VentaCanceladaException;
import org.facturacion.facturacion.repositories.FacturaElectronicaRepository;
import org.facturacion.facturacion.repositories.VentaRepository;
import org.facturacion.facturacion.services.specification.FacturaElectronicaService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * FacturaElectronicaService implementation
 */
@Service
@AllArgsConstructor
public class IFacturaElectronicaService implements FacturaElectronicaService {

        private final FacturaElectronicaRepository facturaElectronicaRepository;
        private final VentaRepository ventaRepository;
        /**
         * Este metodo obtiene todas las facturas electronicas
         * @return List<EFacturaDTO> Lista de facturas electronicas
         */
        @Override
        public List<EFacturaDTO> obtenerFacturasElectronica() {
            return facturaElectronicaRepository
                    .findAll()
                    .stream()
                    .map(EFacturaDTO::fromEntity)
                    .toList();
        }
        /**
         * Este metodo obtiene una factura electronica por su id
         * @param id Id de la factura electronica
         * @return EFacturaDTO Factura electronica
         */
        @Override
        public EFacturaDTO obtenerFacturaElectronicaPorId(Integer id) {
            return facturaElectronicaRepository
                    .findById(id)
                    .map(EFacturaDTO::fromEntity)
                    .orElse(null);
        }

        /**
         * Este metodo guarda una factura electronica
         * @param facturaElectronicaDTO Factura electronica a guardar
         * @return EFacturaDTO Factura electronica guardada
         */
        @Override
        public EFacturaDTO guardarFacturaElectronica(CrearEFacturaDTO facturaElectronicaDTO) {

            Venta venta = ventaRepository.findById(facturaElectronicaDTO.idVenta()).orElse(null);
            if(venta == null) throw new EFacturaNoExisteException("La venta no existe");

            if(facturaElectronicaRepository.existsById(venta.getId())){
                Optional<FacturaElectronica> efactura = facturaElectronicaRepository.findById(venta.getId());
                if(efactura.isPresent()) return EFacturaDTO.fromEntity(efactura.get());
            }

            if(venta.getEstado().equals(EstadoVenta.CANCELADA))
                throw new VentaCanceladaException("La venta esta cancelada");

            return EFacturaDTO.fromEntity(facturaElectronicaRepository.save(facturaElectronicaDTO.toEntity(venta)));
        }

}
