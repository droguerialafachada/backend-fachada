package org.facturacion.facturacion.domain;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UsuarioPermisosID implements Serializable {

    private Integer usuarioId;
    private String permisos;

    // Getters, Setters, hashCode, equals

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getPermisos() {
        return permisos;
    }

    public void setPermisos(String permisos) {
        this.permisos = permisos;
    }

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
