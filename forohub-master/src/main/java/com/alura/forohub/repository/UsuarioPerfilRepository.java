package com.alura.forohub.repository;

import com.alura.forohub.model.UsuarioPerfil;
import com.alura.forohub.model.UsuarioPerfilId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioPerfilRepository extends JpaRepository<UsuarioPerfil, UsuarioPerfilId> {

}
