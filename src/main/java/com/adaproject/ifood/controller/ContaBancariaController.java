package com.adaproject.ifood.controller;

import com.adaproject.ifood.model.ContaBancaria;
import com.adaproject.ifood.model.dto.ContaBancariaDTO;
import com.adaproject.ifood.service.ContaBancariaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contas-bancarias")
@RequiredArgsConstructor
public class ContaBancariaController {

    private final ContaBancariaService contaBancariaService;

    @PostMapping
    public ResponseEntity<?> cadastrarContaBancaria(@RequestBody ContaBancariaDTO contaBancaria) {
        try {
            ContaBancaria novaConta = contaBancariaService.cadastrarContaBancaria(contaBancaria);
            return new ResponseEntity<>(novaConta, HttpStatus.CREATED);
        } catch (Exception e) {
            // Handle exceptions and return an appropriate HTTP status code
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
