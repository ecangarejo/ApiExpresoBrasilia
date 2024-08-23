package com.usuarios.controller;

import com.usuarios.entities.Tarea;
import com.usuarios.service.TareaService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TareaControllerTest {

    @InjectMocks
    private TareaController tareaController;

    @Mock
    private TareaService tareaService;

    public TareaControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCrearTarea_CreacionExitosa() {
        Tarea tarea = new Tarea();
        tarea.setTitulo("Realizar API");
        tarea.setDescripcion("Crud de la API");

        when(tareaService.crearTarea(anyLong(), any(Tarea.class))).thenReturn(tarea);

        ResponseEntity<Tarea> response = tareaController.crearTarea(1L, tarea);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tarea, response.getBody());
        verify(tareaService).crearTarea(1L, tarea);
    }

    @Test
    public void testCrearTarea_TituloODescripcionVacios() {
        Tarea tarea = new Tarea();
        tarea.setTitulo("");
        tarea.setDescripcion("");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            tareaController.crearTarea(1L, tarea);
        });

        assertEquals("El título y la descripción de la tarea no puede ser nulo ni vacío", exception.getMessage());
    }

    @Test
    public void testCrearTarea_UsuarioNoEncontrado() {
        Tarea tarea = new Tarea();
        tarea.setTitulo("Realizar API");
        tarea.setDescripcion("Crud de la API");

        when(tareaService.crearTarea(anyLong(), any(Tarea.class)))
                .thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            tareaController.crearTarea(1L, tarea);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("Usuario no encontrado", exception.getReason());
    }
}