package com.example.projetoBigCloud.controller.request;

import com.example.projetoBigCloud.models.Plano;
import com.example.projetoBigCloud.models.Usuario;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class AssinaturaRequest {
    private UUID id;
    private UUID idUsuario;
    private boolean ativo;
    private Plano plano;
    private List<Usuario> usuarios;
}
