package com.example.projetoBigCloud.repository;

import com.example.projetoBigCloud.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    Optional<Usuario> findUsuarioByEmail(String email);
    Optional<Usuario> findUsuarioByEmailAndSenha(String email, String senha);
}
