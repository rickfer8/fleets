package com.fleets.seguros.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Tuple;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.fleets.seguros.model.Perfil;

@Repository
public class PerfilRepositoryImpl {

	@PersistenceContext
	private EntityManager em;

	public List<Perfil> findPerfil(String parametro) {
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

		Query query = em.createNativeQuery(sql.toString(), Tuple.class);
		queryParams.forEach(query::setParameter);

		List<Tuple> perfis = query.getResultList();
		return perfis.stream().map(tupla -> {
			Perfil perfil = new Perfil();
			perfil.setId(tupla.get("id", Integer.class).longValue());
			perfil.setSigla(tupla.get("sigla", String.class));
			perfil.setDescricao(tupla.get("descricao", String.class));
			return perfil;
		}).collect(Collectors.toList());
	}

}
