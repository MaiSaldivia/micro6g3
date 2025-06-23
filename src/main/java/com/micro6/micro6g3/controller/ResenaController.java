package com.micro6.micro6g3.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.micro6.micro6g3.model.Resena;
import com.micro6.micro6g3.service.ResenaService;

@RestController
@RequestMapping("/api/resenas")
public class ResenaController {

    private final ResenaService resenaService;

    public ResenaController(ResenaService resenaService) {
        this.resenaService = resenaService;
    }

    @GetMapping
    public ResponseEntity<List<Resena>> listarResenas() {
        List<Resena> resenas = resenaService.listarTodas();
        return resenas.isEmpty() ?
            ResponseEntity.noContent().build() :
            ResponseEntity.ok(resenas);
    }

    @GetMapping("/{idProducto}")
    public List<Resena> obtenerPorProducto(@PathVariable UUID idProducto) {
        return resenaService.obtenerPorProducto(idProducto);
    }

    @PostMapping
    public ResponseEntity<Resena> crearResena(@RequestBody Resena resena) {
        if (resena.getIdProducto() == null || resena.getIdUsuario() == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(
            resenaService.dejarResena(
                resena.getIdProducto(),
                resena.getIdUsuario(),
                resena.getComentario(),
                resena.getCalificacion()
            )
        );
    }

    @PutMapping("/{idResena}")
    public ResponseEntity<Resena> actualizarResena(
            @PathVariable UUID idResena,
            @RequestBody Resena resenaActualizada) {

        Resena resena = resenaService.actualizarResena(idResena, resenaActualizada);
        return resena != null
            ? ResponseEntity.ok(resena)
            : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{idResena}")
    public ResponseEntity<Void> eliminarResena(@PathVariable UUID idResena) {
        boolean eliminada = resenaService.eliminarResena(idResena);
        return eliminada
            ? ResponseEntity.noContent().build()
            : ResponseEntity.notFound().build();
    }

}

