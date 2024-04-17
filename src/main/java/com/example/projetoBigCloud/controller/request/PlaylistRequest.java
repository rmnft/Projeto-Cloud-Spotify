package com.example.projetoBigCloud.controller.request;

import com.example.projetoBigCloud.models.Musica;
import com.example.projetoBigCloud.models.Usuario;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class PlaylistRequest {
    private UUID id;

    private UUID idUsuario;

    private String nome;

    private List<Musica> musicas = new ArrayList<>();

    private Usuario usuario;
}
