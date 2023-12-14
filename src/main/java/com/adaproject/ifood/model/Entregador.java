package com.adaproject.ifood.model;

import com.adaproject.ifood.ENUM.TipoDocumento;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Entregador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @NotBlank
    private String numeroDocumento;
    private TipoDocumento tipoDocumento; // Pode ser CPF, RG, CNH, etc.
    private LocalDate dataVencimentoCNH;
    @JsonIgnore
    @OneToOne(mappedBy = "entregador", cascade = CascadeType.ALL)
    private Veiculo veiculo;
}
