package com.usuarios.service;

import com.usuarios.entities.Tarea;
import com.usuarios.entities.Usuario;
import com.usuarios.repository.TareaRepository;
import com.usuarios.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TareaService {

    private final TareaRepository tareaRepository;
    private final UsuarioRepository usuarioRepository;

    public TareaService(TareaRepository tareaRepository, UsuarioRepository usuarioRepository) {
        this.tareaRepository = tareaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Tarea crearTarea(Long usuarioId, Tarea tarea) {
        // Verificar si el usuario existe
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        tarea.setUsuario(usuario);

        return tareaRepository.save(tarea);
    }

    public List<Tarea> obtenerTareasPorUsuario(Long usuarioId) {
        // Verificar si el usuario existe
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        List<Tarea> tareaList = tareaRepository.findByUsuarioId(usuarioId);
        if (tareaList == null || tareaList.isEmpty())
            throw new EntityNotFoundException("No existen tareas para el ID de usuario: " + usuarioId);

        return tareaList;
    }

    public Tarea marcarTareaComoCompletada(Long id) {
        // Verificar si la tarea existe
        Tarea tarea = tareaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Tarea no encontrada"));

        tarea.setEstadoTarea(Tarea.EstadoTarea.Completada);

        return tareaRepository.save(tarea);
    }

    public Map<String, String> eliminarTarea(Long usuarioId, Long tareaId) {
        // Verificar si el usuario existe
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        // Verificar si la tarea existe
        Tarea tarea = tareaRepository.findById(tareaId).orElseThrow(() -> new EntityNotFoundException("Tarea no encontrada"));

        tareaRepository.delete(tarea);

        Map<String, String> response = new HashMap<>();
        response.put("Mensaje", "Tarea con ID: " + tareaId + " eliminada exitosamente");

        return response;
    }

}
