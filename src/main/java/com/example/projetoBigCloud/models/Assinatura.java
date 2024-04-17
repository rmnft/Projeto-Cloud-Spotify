package com.example.projetoBigCloud.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Assinatura {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private UUID idPlano;

    @Column
    private UUID idUsuario;

    @Column
    private boolean ativo;

    @ManyToOne
    @JsonIgnore
    private Plano plano;

    @ManyToOne
    @JsonIgnore
    private Usuario usuario;

}
