package com.micro6.micro6g3.service;

import com.micro6.micro6g3.model.Resena;
import com.micro6.micro6g3.repository.ResenaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

class ResenaServiceTest {

    @Mock
    private ResenaRepository resenaRepository;

    @InjectMocks
    private ResenaService resenaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDejarResena() {
        UUID idProducto = UUID.randomUUID();
        UUID idUsuario = UUID.randomUUID();
        String comentario = "Excelente servicio";
        int calificacion = 5;

        Resena resenaGuardada = new Resena();
        resenaGuardada.setIdProducto(idProducto);
        resenaGuardada.setIdUsuario(idUsuario);
        resenaGuardada.setComentario(comentario);
        resenaGuardada.setCalificacion(calificacion);
        resenaGuardada.setFechaResena(LocalDateTime.now());

        when(resenaRepository.save(any(Resena.class))).thenReturn(resenaGuardada);

        Resena resultado = resenaService.dejarResena(idProducto, idUsuario, comentario, calificacion);

        assertThat(resultado.getComentario()).isEqualTo("Excelente servicio");
        assertThat(resultado.getCalificacion()).isEqualTo(5);
        verify(resenaRepository).save(any(Resena.class));
    }

    @Test
    void testListarTodas() {
        Resena r1 = new Resena();
        Resena r2 = new Resena();
        when(resenaRepository.findAll()).thenReturn(Arrays.asList(r1, r2));

        List<Resena> resultado = resenaService.listarTodas();

        assertThat(resultado).hasSize(2).contains(r1, r2);
        verify(resenaRepository).findAll();
    }

    @Test
    void testObtenerPorProducto() {
        UUID idProducto = UUID.randomUUID();
        Resena r1 = new Resena();
        r1.setIdProducto(idProducto);
        when(resenaRepository.findByIdProducto(idProducto)).thenReturn(List.of(r1));

        List<Resena> resultado = resenaService.obtenerPorProducto(idProducto);

        assertThat(resultado).hasSize(1).contains(r1);
        verify(resenaRepository).findByIdProducto(idProducto);
    }

    @Test
    void testActualizarResena() {
        UUID id = UUID.randomUUID();

        Resena existente = new Resena();
        existente.setComentario("Antiguo");
        existente.setCalificacion(2);

        Resena actualizada = new Resena();
        actualizada.setComentario("Nuevo comentario");
        actualizada.setCalificacion(4);

        when(resenaRepository.findById(id)).thenReturn(Optional.of(existente));
        when(resenaRepository.save(any(Resena.class))).thenReturn(existente);

        Resena resultado = resenaService.actualizarResena(id, actualizada);

        assertThat(resultado.getComentario()).isEqualTo("Nuevo comentario");
        assertThat(resultado.getCalificacion()).isEqualTo(4);
        verify(resenaRepository).save(existente);
    }

    @Test
    void testEliminarResena() {
        UUID id = UUID.randomUUID();

        when(resenaRepository.existsById(id)).thenReturn(true);

        boolean resultado = resenaService.eliminarResena(id);

        assertThat(resultado).isTrue();
        verify(resenaRepository).deleteById(id);
    }
}
