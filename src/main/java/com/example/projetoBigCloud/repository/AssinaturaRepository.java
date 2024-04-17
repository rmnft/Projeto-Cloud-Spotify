package com.example.projetoBigCloud.repository;

import com.example.projetoBigCloud.models.Assinatura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AssinaturaRepository extends JpaRepository<Assinatura, UUID> {
}
