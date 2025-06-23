package com.micro6.micro6g3.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.micro6.micro6g3.model.Resena;

public interface ResenaRepository extends JpaRepository<Resena, UUID> {
    List<Resena> findByIdProducto(UUID idProducto);
}
