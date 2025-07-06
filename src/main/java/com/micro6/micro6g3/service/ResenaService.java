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

    public Resena dejarResena(UUID idProducto, String idUsuario, int idTienda, String comentario, int calificacion) {
        Resena resena = new Resena();
        resena.setIdProducto(idProducto);
        resena.setIdUsuario(idUsuario);
        resena.setIdTienda(idTienda);
        resena.setComentario(comentario);
        resena.setCalificacion(calificacion);
        resena.setFechaResena(LocalDateTime.now());

        return resenaRepository.save(resena);
    }

    public List<Resena> obtenerPorProducto(UUID idProducto) {
        return resenaRepository.findByIdProducto(idProducto);
    }

    public Resena actualizarResena(UUID idResena, Resena resenaActualizada) {
        return resenaRepository.findById(idResena).map(resena -> {
            resena.setComentario(resenaActualizada.getComentario());
            resena.setCalificacion(resenaActualizada.getCalificacion());
            resena.setFechaResena(LocalDateTime.now());
            return resenaRepository.save(resena);
        }).orElse(null);
    }

    public boolean eliminarResena(UUID idResena) {
        if (resenaRepository.existsById(idResena)) {
            resenaRepository.deleteById(idResena);
            return true;
        }
        return false;
    }
}