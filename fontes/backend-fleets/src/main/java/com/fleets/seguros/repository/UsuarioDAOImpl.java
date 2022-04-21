package com.fleets.seguros.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.fleets.seguros.model.Perfil;
import com.fleets.seguros.model.Usuario;

@Repository
public class UsuarioDAOImpl {

	@PersistenceContext
	private EntityManager em;

	public List<Usuario> findByNomeOrEmailOrPerfil(String nome, String email, Integer idPerfil, Boolean ativo) {
		Map<String, Object> queryParams = new HashMap<>();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT u.id, u.nome, u.email, p.id as idPerfil, p.descricao, u.ativo ");
		sql.append("FROM usuario u ");
		sql.append("INNER JOIN perfil p ON p.id = u.id_perfil ");

		if (!StringUtils.isEmpty(nome)) {
			sql.append("WHERE u.nome LIKE :nome ");
			queryParams.put("nome", "%" + nome + "%");
		}

		if (!StringUtils.isEmpty(email)) {
			sql.append(queryParams.isEmpty() ? "WHERE " : "AND ");
			sql.append("u.email LIKE :email ");
			queryParams.put("email", "%" + email + "%");
		}

		if (idPerfil != null && idPerfil > 0) {
			sql.append(queryParams.isEmpty() ? "WHERE " : "AND ");
			sql.append("u.id_perfil = :idPerfil ");
			queryParams.put("idPerfil", idPerfil);
		}

		if (ativo != null) {
			sql.append(queryParams.isEmpty() ? "WHERE " : "AND ");
			sql.append("u.ativo = :ativo ");
			queryParams.put("ativo", ativo);
		}

		sql.append("ORDER BY u.nome");

		Query query = em.createNativeQuery(sql.toString());
		queryParams.forEach((key, value) -> {
			query.setParameter(key, value);
		});

		List<Object[]> usuarios = query.getResultList();
		return usuarios.stream().map(p -> {
			Usuario usuario = new Usuario();
			usuario.setId(((Integer) p[0]).longValue());
			usuario.setNome((String) p[1]);
			usuario.setEmail((String) p[2]);

			Perfil perfil = new Perfil();
			perfil.setId(((Integer) p[3]).longValue());
			perfil.setDescricao((String) p[4]);

			usuario.setPerfil(perfil);
			usuario.setAtivo((Boolean) p[5]);

			return usuario;
		}).collect(Collectors.toList());
	}

}
