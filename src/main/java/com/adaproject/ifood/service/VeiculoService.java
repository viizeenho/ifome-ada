package com.adaproject.ifood.service;

import com.adaproject.ifood.exception.EntregadorNaoEncontradoException;
import com.adaproject.ifood.exception.PlacaInvalidaException;
import com.adaproject.ifood.exception.RenavamDuplicadoException;
import com.adaproject.ifood.mapper.VeiculoMapper;
import com.adaproject.ifood.model.Entregador;
import com.adaproject.ifood.model.Veiculo;
import com.adaproject.ifood.model.dto.VeiculoDTO;
import com.adaproject.ifood.repository.VeiculoRepository;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VeiculoService {

    @Autowired
    private final VeiculoRepository veiculoRepository;

    @Autowired
    private final VeiculoMapper veiculoMapper;

    @Autowired
    private final EntregadorService entregadorService;

    public VeiculoService(VeiculoRepository veiculoRepository, VeiculoMapper veiculoMapper, EntregadorService entregadorService) {
        this.veiculoRepository = veiculoRepository;
        this.veiculoMapper = veiculoMapper;
        this.entregadorService = entregadorService;
    }

    public Veiculo cadastrarVeiculo(@Valid VeiculoDTO veiculoDTO) {
        Veiculo veiculo= new Veiculo();
        Entregador entregador = new Entregador();
        BeanUtils.copyProperties(veiculoDTO, veiculo);
//        Veiculo veiculo = veiculoMapper.toVeiculo(veiculoDTO);

        if (veiculoDTO.getEntregadorId() != null){
             entregador = entregadorService.encontrarEntregadorPorId(veiculoDTO.getEntregadorId());

            if (entregador == null) {
                throw new EntregadorNaoEncontradoException("Entregador não encontrado com ID: " + veiculoDTO.getEntregadorId());
            }
            veiculo.setEntregador(entregador);
        }

        validarAnoModelo(veiculoDTO);
        validarRenavam(veiculoDTO);

        return veiculoRepository.save(veiculo);
    }

    public void validarVeiculo(VeiculoDTO veiculoDTO) {
        validarAnoModelo(veiculoDTO);
        validarRenavam(veiculoDTO);
    }

    public void validarPlaca(String placa) {
        placa = placa.trim();
        String regex = "[A-Z]{3}[0-9]{1}[A-Z0-9]{2}[0-9]{2}";

        // Validar a placa
        if (!placa.matches(regex)) {
            throw new PlacaInvalidaException("Placa inválida: " + placa);
        }
    }


    private void validarAnoModelo(VeiculoDTO veiculoDTO) {
        Integer anoModelo = veiculoDTO.getAnoModelo();

        if (anoModelo != null && anoModelo < 2010) {
            throw new ValidationException("O ano do modelo do veículo não pode ser inferior a 2010.");
        }
    }

    private void validarRenavam(VeiculoDTO veiculoDTO) {
        String renavam = veiculoDTO.getRenavam();

        // Verificar se o RENAVAM já existe no sistema
        if (veiculoRepository.findByRenavam(renavam).isPresent()) {
            throw new RenavamDuplicadoException("Já existe um veículo cadastrado com o mesmo RENAVAM: " + renavam);
        }
    }
}
