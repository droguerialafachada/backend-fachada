package org.facturacion.facturacion.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "usuario_permisos")
public class UsuarioPermisos implements Serializable {

    @EmbeddedId
    private UsuarioPermisosID id;

    // Getters, Setters, hashCode, equals

    public UsuarioPermisosID getId() {
        return id;
    }

    public void setId(UsuarioPermisosID id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioPermisos that = (UsuarioPermisos) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
