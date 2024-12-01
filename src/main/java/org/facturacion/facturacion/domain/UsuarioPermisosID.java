package org.facturacion.facturacion.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
public class UsuarioPermisosID implements Serializable {

    private Integer usuarioId;
    private String permisos;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioPermisosID that = (UsuarioPermisosID) o;
        return Objects.equals(usuarioId, that.usuarioId) &&
                Objects.equals(permisos, that.permisos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuarioId, permisos);
    }
}
