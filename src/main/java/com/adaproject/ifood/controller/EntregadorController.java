package com.adaproject.ifood.controller;

import com.adaproject.ifood.exception.NumberDocumentException;
import com.adaproject.ifood.exception.ValidarDataException;
import com.adaproject.ifood.model.Entregador;
import com.adaproject.ifood.model.dto.EntregadorDTO;
import com.adaproject.ifood.service.EntregadorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entregadores")
@Validated
public class EntregadorController {

    @Autowired
    private EntregadorService entregadorService;

    @PostMapping
    public ResponseEntity<?> cadastrarEntregador(@RequestBody @Valid EntregadorDTO entregadorDTO) {
        try {
            Entregador entregador = entregadorService.cadastrarEntregador(entregadorDTO);
            return ResponseEntity.ok(entregador);
        }catch (NumberDocumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro para cadastrar entregador: " + e.getMessage());
        }catch (ValidarDataException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro para cadastrar entregador: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Entregador>> getAllEntregadores(){

        List<Entregador> entregadores = entregadorService.getAllEntregadores();

        if(!entregadores.isEmpty()){
            return new ResponseEntity<>(entregadores, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);

        }
    }
}
