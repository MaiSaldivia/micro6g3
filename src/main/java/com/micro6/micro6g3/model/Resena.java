package com.micro6.micro6g3.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "resena")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resena {

    @Id
    @GeneratedValue
    private UUID idResena;

    @Column(nullable = false)
    private UUID idProducto;

    @Column(nullable = false)
    private UUID idUsuario;

    private String comentario;

    private int calificacion; // del 1 al 5

    private LocalDateTime fechaResena;
}
