package com.example.projetoBigCloud.repository;

import com.example.projetoBigCloud.models.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PlaylistRepository extends JpaRepository<Playlist, UUID> {
}
