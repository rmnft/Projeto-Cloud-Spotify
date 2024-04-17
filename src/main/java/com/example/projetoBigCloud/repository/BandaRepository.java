package com.example.projetoBigCloud.repository;

import com.example.projetoBigCloud.models.Banda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BandaRepository extends JpaRepository<Banda, UUID> {
}
