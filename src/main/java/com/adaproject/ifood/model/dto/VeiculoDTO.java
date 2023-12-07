package com.adaproject.ifood.model.dto;

import com.adaproject.ifood.model.Entregador;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;



@Data
public class VeiculoDTO {

    @NotBlank
    private String modelo;

    @NotBlank
    private String placa;

    @NotBlank
    private String renavam;

    @NotNull
    private Integer anoModelo;

    @NotNull
    private Long entregadorId;

}
