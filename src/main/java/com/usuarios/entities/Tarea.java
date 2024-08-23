package com.usuarios.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String descripcion;

    @Enumerated(EnumType.STRING)
    private EstadoTarea estadoTarea = EstadoTarea.Pendiente;

    @ManyToOne(targetEntity = Usuario.class)
    @JoinColumn(name = "usuario_id")
    @JsonBackReference
    private Usuario usuario;

    public enum EstadoTarea {
        Pendiente, Completada
    }
}
