package com.micro6.micro6g3.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.micro6.micro6g3.model.Resena;
import com.micro6.micro6g3.repository.ResenaRepository;

@Service
public class ResenaService {

    private final ResenaRepository resenaRepository;

    public ResenaService(ResenaRepository resenaRepository) {
        this.resenaRepository = resenaRepository;
    }

    public List<Resena> listarTodas() {
        return resenaRepository.findAll();
    }

    public Resena dejarResena(UUID idProducto, UUID idUsuario, String comentario, int calificacion) {
        Resena resena = new Resena();
        resena.setIdProducto(idProducto);
        resena.setIdUsuario(idUsuario);
        resena.setComentario(comentario);
        resena.setCalificacion(calificacion);
        resena.setFechaResena(LocalDateTime.now());

        return resenaRepository.save(resena);
    }

    public List<Resena> obtenerPorProducto(UUID idProducto) {
        return resenaRepository.findByIdProducto(idProducto);
    }
}
