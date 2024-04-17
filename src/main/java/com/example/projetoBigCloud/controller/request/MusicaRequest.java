package com.example.projetoBigCloud.controller.request;

import com.example.projetoBigCloud.models.Banda;
import lombok.Data;

import java.util.UUID;

@Data
public class MusicaRequest {
    private UUID id;
    private String nome;
    private Integer duracao;
    private Banda banda;

}
