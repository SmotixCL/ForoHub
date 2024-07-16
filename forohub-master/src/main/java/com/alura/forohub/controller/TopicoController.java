package com.alura.forohub.controller;

import com.alura.forohub.model.Topico;
import com.alura.forohub.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    // Listado de todos los tópicos con paginación opcional y ordenación por fecha de creación
    @GetMapping
    public ResponseEntity<List<Topico>> getAllTopicos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "fechaCreacion") String sortBy,
            @RequestParam(defaultValue = "asc") String order
    ) {
        Sort.Direction sortDirection = order.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));
        List<Topico> topicos = topicoRepository.findAll(pageable).getContent();
        return ResponseEntity.ok(topicos);
    }

    // Detalle de un tópico por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getTopicoById(@PathVariable Long id) {
        Optional<Topico> optionalTopico = topicoRepository.findById(id);
        if (optionalTopico.isPresent()) {
            return ResponseEntity.ok(optionalTopico.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Topico no encontrado con id: " + id);
        }
    }

    // Registro de un nuevo tópico
    @PostMapping
    public ResponseEntity<?> createTopico(@Valid @RequestBody Topico topico) {
        // Verificar si ya existe un tópico con el mismo título y mensaje
        if (topicoRepository.existsByTituloAndMensaje(topico.getTitulo(), topico.getMensaje())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Ya existe un tópico con el mismo título y mensaje");
        }
        Topico nuevoTopico = topicoRepository.save(topico);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoTopico);
    }

    // Actualización de un tópico por ID
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTopico(@PathVariable Long id, @Valid @RequestBody Topico topicoActualizado) {
        Optional<Topico> optionalTopico = topicoRepository.findById(id);
        if (optionalTopico.isPresent()) {
            Topico topicoExistente = optionalTopico.get();
            // Verificar si se está intentando cambiar el título o el mensaje a uno ya existente
            if (!topicoExistente.getId().equals(topicoActualizado.getId()) &&
                    topicoRepository.existsByTituloAndMensaje(topicoActualizado.getTitulo(), topicoActualizado.getMensaje())) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Ya existe un tópico con el mismo título y mensaje");
            }
            // Actualizar los datos del tópico existente
            topicoExistente.setTitulo(topicoActualizado.getTitulo());
            topicoExistente.setMensaje(topicoActualizado.getMensaje());
            topicoExistente.setFechaCreacion(topicoActualizado.getFechaCreacion());
            topicoExistente.setStatus(topicoActualizado.getStatus());
            topicoExistente.setAutor(topicoActualizado.getAutor());
            topicoExistente.setCurso(topicoActualizado.getCurso());
            return ResponseEntity.ok(topicoRepository.save(topicoExistente));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Topico no encontrado con id: " + id);
        }
    }

    // Eliminación de un tópico por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTopico(@PathVariable Long id) {
        Optional<Topico> optionalTopico = topicoRepository.findById(id);
        if (optionalTopico.isPresent()) {
            topicoRepository.deleteById(id);
            return ResponseEntity.ok("Topico eliminado con éxito");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Topico no encontrado con id: " + id);
        }
    }
}
