package com.adaproject.ifood.repository;

import com.adaproject.ifood.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    Optional<Object> findByRenavam(String renavam);
}
