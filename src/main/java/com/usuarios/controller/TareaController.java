package com.usuarios.controller;

import com.usuarios.entities.Tarea;
import com.usuarios.service.TareaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tarea")
public class TareaController {

    private final TareaService tareaService;

    public TareaController(TareaService tareaService) {
        this.tareaService = tareaService;
    }

    @PostMapping("/{usuarioId}")
    public ResponseEntity<Tarea> crearTarea(@PathVariable Long usuarioId, @RequestBody Tarea tarea) {
        if (tarea.getTitulo() == null || tarea.getTitulo().isEmpty() || tarea.getDescripcion() == null || tarea.getDescripcion().isEmpty()) {
            throw new IllegalArgumentException("El título y la descripción de la tarea no puede ser nulo ni vacío");
        }
        return new ResponseEntity<>(this.tareaService.crearTarea(usuarioId, tarea), HttpStatus.OK);
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<List<Tarea>> obtenerTareasPorUsuario(@PathVariable Long usuarioId) {
        return new ResponseEntity<>(this.tareaService.obtenerTareasPorUsuario(usuarioId), HttpStatus.OK);
    }

    @PutMapping("/{id}/completar")
    public ResponseEntity<Tarea> marcarTareaComoCompletada(@PathVariable Long id) {
        return ResponseEntity.ok(tareaService.marcarTareaComoCompletada(id));
    }

    @DeleteMapping("/{usuarioId}/{tareaId}")
    public ResponseEntity<Map<String, String>> eliminarTarea(@PathVariable Long usuarioId, @PathVariable Long tareaId) {
        Map<String, String> response = tareaService.eliminarTarea(usuarioId, tareaId);
        return ResponseEntity.ok(response);
    }

}
