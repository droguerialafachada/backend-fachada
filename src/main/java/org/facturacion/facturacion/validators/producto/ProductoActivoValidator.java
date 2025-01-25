package org.facturacion.facturacion.validators.producto;

import org.facturacion.facturacion.dto.producto.ActualizarProductoDTO;
import org.facturacion.facturacion.dto.producto.CrearProductoDTO;
import org.facturacion.facturacion.exceptions.producto.ProductoActivoException;
import org.facturacion.facturacion.validators.producto.interfaces.ProductoValidator;
import org.springframework.stereotype.Component;

@Component
public class ProductoActivoValidator implements ProductoValidator<Object> {


    @Override
    public void validate(Object objectDTO) {
        if (objectDTO instanceof CrearProductoDTO crearProductoDTO) {
            if (!crearProductoDTO.activo().equals("1")) {
                throw new ProductoActivoException("El producto debe estar activo");
            }
        } else if (objectDTO instanceof ActualizarProductoDTO actualizarProductoDTO && !actualizarProductoDTO.activo()) {
                throw new ProductoActivoException("El producto debe estar activo");
            }
    }
}
