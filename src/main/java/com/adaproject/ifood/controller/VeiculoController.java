package com.adaproject.ifood.controller;

import com.adaproject.ifood.exception.EntregadorNaoEncontradoException;
import com.adaproject.ifood.exception.RenavamDuplicadoException;
import com.adaproject.ifood.model.Veiculo;
import com.adaproject.ifood.model.dto.VeiculoDTO;
import com.adaproject.ifood.service.VeiculoService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/veiculos")
@Validated
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @PostMapping
    public ResponseEntity<?> cadastrarVeiculo(@RequestBody @Valid VeiculoDTO veiculoDTO) {
        try {
            Veiculo veiculo = veiculoService.cadastrarVeiculo(veiculoDTO);
            return ResponseEntity.ok(veiculo);
        } catch (RenavamDuplicadoException e) {
            // Captura específica para RenavamDuplicadoException
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro durante o cadastro do veículo: " + e.getMessage());
        } catch (EntregadorNaoEncontradoException e) {
            // Tratamento genérico para outras exceções
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Nao foi encontrado nenhum entregador: " + e.getMessage());
        }catch (Exception e) {
            // Tratamento genérico para outras exceções
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro durante o cadastro do veículo: " + e.getMessage());
        }
    }



}
