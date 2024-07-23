package org.facturacion.facturacion.dto.validationError;

import java.util.Date;

public record ValidationError(
        Date fecha,
        String mensaje,
        String path
) {
}
