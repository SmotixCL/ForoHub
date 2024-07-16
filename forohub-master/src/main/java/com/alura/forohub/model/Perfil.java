package com.alura.forohub.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "perfil")
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    // Relación con UsuarioPerfil
    @OneToMany(mappedBy = "perfil")
    private List<UsuarioPerfil> usuarios;

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<UsuarioPerfil> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<UsuarioPerfil> usuarios) {
        this.usuarios = usuarios;
    }
}
