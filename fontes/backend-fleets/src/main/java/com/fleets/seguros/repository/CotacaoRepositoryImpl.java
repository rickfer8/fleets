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

import com.fleets.seguros.model.Cotacao;

@Repository
public class CotacaoRepositoryImpl {
	
	@PersistenceContext
	private EntityManager em;
	
	public List<Cotacao> findCotacao(String parametro) {
		Map<String, Object> queryParams = new HashMap<>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT c.id, c.placa, c.chassi, c.codigo_fipe, c.marca, c.modelo, c.cidade, c.uf ");
		sql.append("FROM cotacao c ");
		
		if (!StringUtils.isEmpty(parametro)) {
			sql.append("WHERE lower(c.placa) LIKE :parametro ");
			sql.append("OR lower(c.chassi) LIKE :parametro ");
			sql.append("OR lower(c.codigo_fipe) LIKE :parametro ");
			sql.append("OR lower(c.marca) LIKE :parametro ");
			sql.append("OR lower(c.modelo) LIKE :parametro ");
			sql.append("OR lower(c.cidade) LIKE :parametro ");
			sql.append("OR lower(c.uf) LIKE :parametro ");
			
			queryParams.put("parametro", "%" + parametro.toLowerCase() + "%");
		}
		
		sql.append("ORDER BY c.id DESC");
		
		Query query = em.createNativeQuery(sql.toString(), Tuple.class);
		queryParams.forEach(query::setParameter);
		
		List<Tuple> cotacoes = query.getResultList();
		return cotacoes.stream().map(tupla -> {
			Cotacao cotacao = new Cotacao();
			cotacao.setId(tupla.get("id", Integer.class).longValue());
			cotacao.setPlaca(tupla.get("placa", String.class));
			cotacao.setChassi(tupla.get("chassi", String.class));
			cotacao.setCodigoFipe(tupla.get("codigo_fipe", Integer.class));
			cotacao.setMarca(tupla.get("marca", String.class));
			cotacao.setModelo(tupla.get("modelo", String.class));
			cotacao.setCidade(tupla.get("cidade", String.class));
			cotacao.setUf(tupla.get("uf", String.class));
			return cotacao;
		}).collect(Collectors.toList());
	}
}
