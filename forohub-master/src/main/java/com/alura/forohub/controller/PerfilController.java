package com.alura.forohub.controller;

import com.alura.forohub.model.Perfil;
import com.alura.forohub.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/perfiles")
public class PerfilController {

    @Autowired
    private PerfilRepository perfilRepository;

    @GetMapping
    public List<Perfil> getAllPerfiles() {
        return perfilRepository.findAll();
    }

    @GetMapping("/{id}")
    public Perfil getPerfilById(@PathVariable Long id) {
        return perfilRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Perfil no encontrado con id: " + id));
    }

    @PostMapping
    public Perfil createPerfil(@RequestBody Perfil perfil) {
        return perfilRepository.save(perfil);
    }

    @PutMapping("/{id}")
    public Perfil updatePerfil(@PathVariable Long id, @RequestBody Perfil perfilActualizado) {
        return perfilRepository.findById(id)
                .map(perfil -> {
                    perfil.setNombre(perfilActualizado.getNombre());
                    return perfilRepository.save(perfil);
                })
                .orElseThrow(() -> new RuntimeException("Perfil no encontrado con id: " + id));
    }

    @DeleteMapping("/{id}")
    public void deletePerfil(@PathVariable Long id) {
        perfilRepository.deleteById(id);
    }
}
