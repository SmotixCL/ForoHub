package com.alura.forohub.model;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario_perfil")
public class UsuarioPerfil {

    @EmbeddedId
    private UsuarioPerfilId id;

    @ManyToOne
    @MapsId("usuarioId")
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @MapsId("perfilId")
    @JoinColumn(name = "perfil_id")
    private Perfil perfil;

    // Getters y setters

    public UsuarioPerfilId getId() {
        return id;
    }

    public void setId(UsuarioPerfilId id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
}
