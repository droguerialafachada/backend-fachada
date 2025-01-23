package org.facturacion.facturacion.validators.producto;

import lombok.AllArgsConstructor;
import org.facturacion.facturacion.domain.TipoImpuesto;
import org.facturacion.facturacion.dto.producto.CrearProductoDTO;
import org.facturacion.facturacion.exceptions.producto.ProductoImpuestoException;
import org.facturacion.facturacion.repositories.TipoImpuestoRepository;
import org.facturacion.facturacion.validators.producto.interfaces.ProductoValidator;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductoImpuestoValidator implements ProductoValidator<Object> {

    private final TipoImpuestoRepository tipoImpuestoRepository;

    @Override
    public void validate(Object dto) {

        String impuesto = null;
        if (dto instanceof CrearProductoDTO crearProductoDTO) {
            impuesto = crearProductoDTO.impuesto();
            if (impuesto == null || impuesto.isEmpty()) {
                throw new ProductoImpuestoException("El impuesto del producto no puede estar vacío");
            }
            if (!tipoImpuestoRepository.existsByNombre(impuesto)) {
                throw new ProductoImpuestoException("El impuesto del producto no existe");
            }
        }

    }


}
