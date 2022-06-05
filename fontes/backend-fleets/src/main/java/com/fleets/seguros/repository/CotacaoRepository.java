package com.fleets.seguros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fleets.seguros.model.Cotacao;

@Repository
public interface CotacaoRepository extends JpaRepository<Cotacao, Long> {

}
