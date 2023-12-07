package com.adaproject.ifood.model.dto;

import com.adaproject.ifood.ENUM.TipoDocumento;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
public class EntregadorDTO {

    @NotNull
    private String nome;

    @NotNull
    private String numeroDocumento;

    @NotNull
    private TipoDocumento tipoDocumento;

    private LocalDate dataVencimentoCNH;


}
