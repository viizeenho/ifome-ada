package com.adaproject.ifood.repository;

import com.adaproject.ifood.model.Entregador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EntregadorRepository extends JpaRepository<Entregador, Long> {

    Optional<Entregador> findById(Long id);
}
