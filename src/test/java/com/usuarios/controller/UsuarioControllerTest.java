package com.usuarios.controller;

import com.usuarios.entities.Usuario;
import com.usuarios.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UsuarioControllerTest {

    @InjectMocks
    private UsuarioController usuarioController;

    @Mock
    private UsuarioService usuarioService;

    public UsuarioControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCrearUsuario_CreacionExitosa() {
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario("Tony Stark");

        when(usuarioService.crearUsuario(any(Usuario.class))).thenReturn(usuario);

        ResponseEntity<Usuario> response = usuarioController.crearUsuario(usuario);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(usuario, response.getBody());
        verify(usuarioService).crearUsuario(usuario);
    }

    @Test
    public void testCrearUsuario_NombreUsuarioVacio() {
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario("");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            usuarioController.crearUsuario(usuario);
        });

        assertEquals("El nombre de usuario no puede ser nulo ni vacío", exception.getMessage());
    }

    @Test
    public void testCrearUsuario_UsuarioExistente() {
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario("Tony Stark");

        when(usuarioService.crearUsuario(any(Usuario.class)))
                .thenThrow(new IllegalArgumentException("Este usuario ya está creado"));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            usuarioController.crearUsuario(usuario);
        });

        assertEquals("Este usuario ya está creado", exception.getMessage());
    }
}