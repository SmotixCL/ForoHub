package com.alura.forohub.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class UsuarioPerfilId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "usuario_id")
    private Long usuarioId;

    @Column(name = "perfil_id")
    private Long perfilId;

    public UsuarioPerfilId() {
    }

    public UsuarioPerfilId(Long usuarioId, Long perfilId) {
        this.usuarioId = usuarioId;
        this.perfilId = perfilId;
    }

    // Getters y setters

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getPerfilId() {
        return perfilId;
    }

    public void setPerfilId(Long perfilId) {
        this.perfilId = perfilId;
    }

    // hashCode y equals (necesarios para claves primarias compuestas)

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioPerfilId that = (UsuarioPerfilId) o;
        return Objects.equals(usuarioId, that.usuarioId) &&
                Objects.equals(perfilId, that.perfilId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuarioId, perfilId);
    }
}
