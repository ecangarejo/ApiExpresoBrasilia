package com.usuarios.service;

import com.usuarios.entities.Usuario;
import com.usuarios.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario crearUsuario(Usuario usuario) {
        // Verificar si el usuario existe
        Optional<Usuario> optUsuario = usuarioRepository.findByNombreUsuario(usuario.getNombreUsuario());
        if (optUsuario.isPresent())
            throw new IllegalArgumentException("Este usuario ya está creado");

        return usuarioRepository.save(usuario);
    }

    public List<Usuario> obtenerUsuarios() {
        List<Usuario> usuarioList = usuarioRepository.findAll();
        if (usuarioList == null || usuarioList.isEmpty())
            throw new EntityNotFoundException("No existen usuarios");

        return usuarioList;
    }

    public Optional<Usuario> obtenerUsuarioPorId(Long id) {
        // Verificar si el usuario existe
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        return usuarioRepository.findById(id);
    }

}
