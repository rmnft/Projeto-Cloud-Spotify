package com.example.projetoBigCloud.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Banda {

    public Banda() {
        this.musicas = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private String nome;

    @Column
    private String descricao;

    @Column
    private String backDrop;

    @OneToMany
    @JoinColumn(name = "banda_id", referencedColumnName = "id")
    private List<Musica> musicas;
}
