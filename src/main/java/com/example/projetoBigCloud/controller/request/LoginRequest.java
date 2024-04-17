package com.example.projetoBigCloud.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {

    @NotBlank(message = "Campo email obrigatório")
    private String email;

    @NotBlank(message = "Campo senha obrigatório")
    private String senha;

}
