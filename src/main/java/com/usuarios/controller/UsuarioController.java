package com.usuarios.controller;

import com.usuarios.entities.Usuario;
import com.usuarios.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping()
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        if (usuario.getNombreUsuario() == null || usuario.getNombreUsuario().isEmpty()) {
            throw new IllegalArgumentException("El nombre de usuario no puede ser nulo ni vacío");
        }
        return new ResponseEntity<>(this.usuarioService.crearUsuario(usuario), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerUsuarios() {
        return new ResponseEntity<>(this.usuarioService.obtenerUsuarios(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Usuario>> obtenerUsuarioPorId(@PathVariable Long id) {
        return new ResponseEntity<>(this.usuarioService.obtenerUsuarioPorId(id), HttpStatus.OK);
    }

}
