package com.adaproject.ifood.model.dto;

import lombok.Data;

@Data
public class ContaBancariaDTO {
    private String numeroAgencia;
    private String numeroConta;
    private String tipoConta;
    private String instituicaoBancaria;
    private Long idTitular;

}
