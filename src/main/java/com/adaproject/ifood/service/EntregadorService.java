package com.adaproject.ifood.service;

import com.adaproject.ifood.ENUM.TipoDocumento;
import com.adaproject.ifood.exception.NumberDocumentException;
import com.adaproject.ifood.exception.ValidarDataException;
import com.adaproject.ifood.model.Entregador;
import com.adaproject.ifood.model.dto.EntregadorDTO;
import com.adaproject.ifood.repository.EntregadorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class EntregadorService {

    @Autowired
    private EntregadorRepository entregadorRepository;

    public Entregador cadastrarEntregador(@Valid EntregadorDTO entregadorDTO) {
        Entregador entregador = new Entregador();
        BeanUtils.copyProperties(entregadorDTO, entregador);

        // Lógica de validação adicional
        validarDocumento(entregadorDTO);

        return entregadorRepository.save(entregador);
    }

    public void deletarEntregadorPorId(Long entregadorId) {
        Entregador entregador = encontrarEntregadorPorId(entregadorId);
        entregadorRepository.delete(entregador);
    }

    public List<Entregador> getAllEntregadores() {
        return entregadorRepository.findAll();
    }

    public void validarDocumento(EntregadorDTO entregadorDTO) {
        validarNumeroDocumento(entregadorDTO);
        validarDataVencimentoCNH(entregadorDTO);
    }

    private void validarNumeroDocumento(EntregadorDTO entregadorDTO) {
        String numeroDocumento = entregadorDTO.getNumeroDocumento();
        TipoDocumento tipoDocumento = entregadorDTO.getTipoDocumento();

        switch (tipoDocumento) {
            case CPF:
                if (numeroDocumento.length() != 11) {
                    throw new NumberDocumentException("CPF deve conter 11 dígitos.");
                }
                break;
            case RG:
                if (numeroDocumento.length() != 9) {
                    throw new NumberDocumentException("RG deve conter 7 dígitos.");
                }
                break;
            case CNH:
                if (numeroDocumento.length() != 11) {
                    throw new NumberDocumentException("CNH deve conter 11 dígitos.");
                }
                break;
        }
    }

    private void validarDataVencimentoCNH(EntregadorDTO entregadorDTO) {
        LocalDate dataVencimentoCNH = entregadorDTO.getDataVencimentoCNH();

        if (dataVencimentoCNH != null && dataVencimentoCNH.isBefore(LocalDate.now())) {
            throw new ValidarDataException("A data de vencimento da CNH não pode ser anterior à data atual.");
        }
    }

    public Entregador encontrarEntregadorPorId(Long entregadorId) {
        Optional<Entregador> entregadorOptional = entregadorRepository.findById(entregadorId);

        if (entregadorOptional.isPresent()) {
            return entregadorOptional.get();
        } else {
            throw new RuntimeException("Entregador não encontrado com ID: " + entregadorId);
        }
    }
}
