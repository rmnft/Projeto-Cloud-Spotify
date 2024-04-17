package com.example.projetoBigCloud.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private String nome;

    @Column
    @Email
    private String email;

    @Column
    private String senha;

    @OneToMany
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private List<Assinatura> assinaturas;

    @OneToMany
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private List<Playlist> playlists;

}
