package com.adaproject.ifood.service;


import com.adaproject.ifood.exception.*;
import com.adaproject.ifood.model.ContaBancaria;
import com.adaproject.ifood.model.Entregador;
import com.adaproject.ifood.model.dto.ContaBancariaDTO;
import com.adaproject.ifood.repository.ContaBancariaRepository;
import com.adaproject.ifood.validator.ContaBancariaValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContaBancariaService {

    private final ContaBancariaRepository contaBancariaRepository;
    private final EntregadorService entregadorService;

    public ContaBancaria cadastrarContaBancaria(ContaBancariaDTO contaBancariaDTO) {

        ContaBancaria contaBancaria = new ContaBancaria();
        Entregador entregador = new Entregador();
        BeanUtils.copyProperties(contaBancariaDTO, contaBancaria);

        if (contaBancariaDTO.getIdTitular() != null){
            entregador = entregadorService.encontrarEntregadorPorId(contaBancariaDTO.getIdTitular());

            if (entregador == null) {
                throw new EntregadorNaoEncontradoException("Entregador não encontrado com ID: " + contaBancariaDTO.getIdTitular());
            }
            contaBancaria.setEntregador(entregador);
        }

        validarContaBancaria(contaBancaria);
        return contaBancariaRepository.save(contaBancaria);
    }

    public void validarContaBancaria(ContaBancaria contaBancaria) {
        if (contaBancaria == null) {
            throw new ContaBancariaInvalidaException();
        }

        if (!ContaBancariaValidator.validarNumeroAgencia(contaBancaria.getNumeroAgencia())) {
            throw new NumeroAgenciaInvalidoException();
        }

        if (!ContaBancariaValidator.validarNumeroConta(contaBancaria.getNumeroConta())) {
            throw new NumeroContaInvalidoException();
        }

        if (!ContaBancariaValidator.validarTipoConta(contaBancaria.getTipoConta())) {
            throw new TipoContaInvalidoException();
        }

        if (!ContaBancariaValidator.validarInstituicaoBancaria(contaBancaria.getInstituicaoBancaria())) {
            throw new InstituicaoBancariaInvalidaException();
        }
    }
}