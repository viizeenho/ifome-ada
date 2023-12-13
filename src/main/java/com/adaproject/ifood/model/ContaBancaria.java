package com.adaproject.ifood.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ContaBancaria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroAgencia;
    private String numeroConta;
    private String tipoConta;
    private String instituicaoBancaria;
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "entregador_id")
    private Entregador entregador;
}