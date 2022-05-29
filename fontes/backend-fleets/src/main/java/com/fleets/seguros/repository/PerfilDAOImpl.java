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

@Repository
public class PerfilDAOImpl {

	@PersistenceContext
	private EntityManager em;

	public List<Perfil> findBySiglaOrDescricao(String parametro) {
		Map<String, Object> queryParams = new HashMap<>();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT p.id, p.sigla, p.descricao ");
		sql.append("FROM perfil p ");

		if (!StringUtils.isEmpty(parametro)) {
			sql.append("WHERE lower(p.sigla) LIKE :parametro ");
			sql.append("OR lower(p.descricao) LIKE :parametro ");
			queryParams.put("parametro", "%" + parametro.toLowerCase() + "%");
		}

		sql.append("ORDER BY p.sigla, p.descricao");

		Query query = em.createNativeQuery(sql.toString());
		queryParams.forEach(query::setParameter);

		List<Object[]> perfis = query.getResultList();
		return perfis.stream().map(p -> {
			Perfil perfil = new Perfil();
			perfil.setId(((Integer) p[0]).longValue());
			perfil.setSigla((String) p[1]);
			perfil.setDescricao((String) p[2]);

			return perfil;
		}).collect(Collectors.toList());
	}

}
