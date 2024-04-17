package com.example.projetoBigCloud.repository;

import com.example.projetoBigCloud.models.Plano;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PlanoRepository extends JpaRepository<Plano, UUID> {
}
