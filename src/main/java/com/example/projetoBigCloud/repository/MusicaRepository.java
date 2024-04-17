package com.example.projetoBigCloud.repository;

import com.example.projetoBigCloud.models.Musica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MusicaRepository extends JpaRepository<Musica, UUID> {
}
