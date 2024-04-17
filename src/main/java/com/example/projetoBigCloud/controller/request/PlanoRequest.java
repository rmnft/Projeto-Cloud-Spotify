package com.example.projetoBigCloud.controller.request;

import com.example.projetoBigCloud.models.Assinatura;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class PlanoRequest {
    private UUID id;
    private String nome;
    private Double preco;
    private List<AssinaturaRequest> assinaturas;
}
