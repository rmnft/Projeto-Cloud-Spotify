package com.example.projetoBigCloud.controller.request;

import com.example.projetoBigCloud.models.Musica;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class BandaRequest {
    private UUID id;
    private String nome;
    private String descricao;
    private String backDrop;
    private List<MusicaRequest> musicas;
}
