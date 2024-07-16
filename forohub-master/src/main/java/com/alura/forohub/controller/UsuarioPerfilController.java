package com.alura.forohub.controller;

import com.alura.forohub.model.UsuarioPerfil;
import com.alura.forohub.model.UsuarioPerfilId;
import com.alura.forohub.repository.UsuarioPerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios-perfiles")
public class UsuarioPerfilController {

    @Autowired
    private UsuarioPerfilRepository usuarioPerfilRepository;

    @GetMapping
    public List<UsuarioPerfil> getAllUsuariosPerfiles() {
        return usuarioPerfilRepository.findAll();
    }

    @GetMapping("/{usuarioId}/{perfilId}")
    public UsuarioPerfil getUsuarioPerfilById(@PathVariable Long usuarioId, @PathVariable Long perfilId) {
        UsuarioPerfilId id = new UsuarioPerfilId(usuarioId, perfilId);
        return usuarioPerfilRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Relación Usuario-Perfil no encontrada con id: " + id));
    }

    @PostMapping
    public UsuarioPerfil createUsuarioPerfil(@RequestBody UsuarioPerfil usuarioPerfil) {
        // Asumiendo que usuarioPerfil contiene los ids de usuario y perfil
        UsuarioPerfilId id = new UsuarioPerfilId(usuarioPerfil.getUsuario().getId(), usuarioPerfil.getPerfil().getId());
        usuarioPerfil.setId(id); // Asignamos el id compuesto a la entidad UsuarioPerfil
        return usuarioPerfilRepository.save(usuarioPerfil);
    }

    @DeleteMapping("/{usuarioId}/{perfilId}")
    public void deleteUsuarioPerfil(@PathVariable Long usuarioId, @PathVariable Long perfilId) {
        UsuarioPerfilId id = new UsuarioPerfilId(usuarioId, perfilId);
        usuarioPerfilRepository.deleteById(id);
    }
}
