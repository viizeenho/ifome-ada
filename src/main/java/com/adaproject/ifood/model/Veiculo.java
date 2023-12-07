package com.adaproject.ifood.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String modelo;

    @NotBlank
    private String placa;

    @NotBlank
    private String renavam;

    @NotNull
    private Integer anoModelo;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "entregador_id")
    private Entregador entregador;

}
