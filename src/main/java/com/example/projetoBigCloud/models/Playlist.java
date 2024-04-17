package com.example.projetoBigCloud.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private UUID idUsuario;

    @Column
    private String nome;

    @ManyToMany
    @JoinTable(
          name = "playlist_musica",
          joinColumns = @JoinColumn(name = "playlist_id"),
          inverseJoinColumns = @JoinColumn(name = "musica_id")
    )
    private List<Musica> musicas = new ArrayList<>();

    @ManyToOne
    @JsonIgnore
    private Usuario usuario;
}
