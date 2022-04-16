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

import com.fleets.seguros.enuns.PerfilEnum;
import com.fleets.seguros.model.Perfil;

@Repository
public class PerfilDAOImpl {

	@PersistenceContext
	private EntityManager em;

	public List<Perfil> findBySiglaOrDescricao(String sigla, String descricao) {
		Map<String, Object> queryParams = new HashMap<>();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT p.id, p.sigla, p.descricao ");
		sql.append("FROM perfil p ");

		if (!StringUtils.isEmpty(sigla)) {
			sql.append("WHERE p.sigla LIKE :sigla ");
			queryParams.put("sigla", "%" + sigla + "%");
		}

		if (!StringUtils.isEmpty(descricao)) {
			sql.append(queryParams.isEmpty() ? "WHERE " : "AND ");
			sql.append("p.descricao LIKE :descricao ");
			queryParams.put("descricao", "%" + descricao + "%");
		}

		sql.append("ORDER BY p.sigla, p.descricao");

		Query query = em.createNativeQuery(sql.toString());
		queryParams.forEach((key, value) -> {
			query.setParameter(key, value);
		});

		List<Object[]> resultados = query.getResultList();
		return resultados.stream().map(p -> {
			Perfil perfil = new Perfil();
			perfil.setId(((Integer) p[0]).longValue());
			perfil.setSigla((String) p[1]);
			perfil.setDescricao(PerfilEnum.valueOf((String) p[2]));

			return perfil;
		}).collect(Collectors.toList());
	}

}
