package com.fleets.seguros.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fleets.seguros.model.Usuario;

@Repository
public interface UsuarioDAO extends JpaRepository<Usuario, Long> {
	
	@Query("SELECT u FROM usuario u WHERE u.email = ?1 AND u.senha = ?2")
	public Optional<Usuario> login(String email, String senha);
	
	@Query("SELECT u FROM usuario u WHERE u.email = ?1")
	public Optional<Usuario> findByEmail(String email);

}
