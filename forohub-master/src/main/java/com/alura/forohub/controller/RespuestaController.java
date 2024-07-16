package com.alura.forohub.controller;

import com.alura.forohub.model.Respuesta;
import com.alura.forohub.repository.RespuestaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/respuestas")
public class RespuestaController {

    @Autowired
    private RespuestaRepository respuestaRepository;

    @GetMapping
    public List<Respuesta> getAllRespuestas() {
        return respuestaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Respuesta getRespuestaById(@PathVariable Long id) {
        return respuestaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Respuesta no encontrada con id: " + id));
    }

    @PostMapping
    public Respuesta createRespuesta(@RequestBody Respuesta respuesta) {
        return respuestaRepository.save(respuesta);
    }

    @PutMapping("/{id}")
    public Respuesta updateRespuesta(@PathVariable Long id, @RequestBody Respuesta respuestaActualizada) {
        return respuestaRepository.findById(id)
                .map(respuesta -> {
                    respuesta.setMensaje(respuestaActualizada.getMensaje());
                    respuesta.setFechaCreacion(respuestaActualizada.getFechaCreacion());
                    respuesta.setSolucion(respuestaActualizada.isSolucion());
                    respuesta.setAutor(respuestaActualizada.getAutor());
                    respuesta.setTopico(respuestaActualizada.getTopico());
                    return respuestaRepository.save(respuesta);
                })
                .orElseThrow(() -> new RuntimeException("Respuesta no encontrada con id: " + id));
    }

    @DeleteMapping("/{id}")
    public void deleteRespuesta(@PathVariable Long id) {
        respuestaRepository.deleteById(id);
    }
}
