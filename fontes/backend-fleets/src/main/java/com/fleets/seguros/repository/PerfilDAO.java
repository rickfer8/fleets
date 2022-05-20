package com.fleets.seguros.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fleets.seguros.model.Perfil;

@Repository
public interface PerfilDAO extends JpaRepository<Perfil, Long> {

	@Query("SELECT p FROM perfil p WHERE p.descricao = :descricao")
	public Optional<Perfil> findDescricao(String descricao);

}
