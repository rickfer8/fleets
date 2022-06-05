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
import org.springframework.web.bind.annotation.RequestParam;

import com.fleets.seguros.model.Perfil;
import com.fleets.seguros.model.Usuario;

@Repository
public class UsuarioRepositoryImpl {

	@PersistenceContext
	private EntityManager em;

	public List<Usuario> findByNomeOrEmailOrPerfil(@RequestParam String parametro) {
		Map<String, Object> queryParams = new HashMap<>();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT u.id, u.nome, u.email, u.cpf, p.id as idPerfil, p.sigla, p.descricao, u.ativo ");
		sql.append("FROM usuario u ");
		sql.append("INNER JOIN perfil p ON p.id = u.id_perfil ");

		if (!StringUtils.isEmpty(parametro)) {
			sql.append("WHERE lower(u.nome) LIKE :parametro ");
			sql.append("OR lower(u.email) LIKE :parametro ");
			sql.append("OR lower(p.descricao) LIKE :parametro ");
			queryParams.put("parametro", "%" + parametro + "%");
		}

		sql.append("ORDER BY u.nome ");

		Query query = em.createNativeQuery(sql.toString());
		queryParams.forEach(query::setParameter);

		List<Object[]> usuarios = query.getResultList();
		return usuarios.stream().map(p -> {
			Usuario usuario = new Usuario();
			usuario.setId(((Integer) p[0]).longValue());
			usuario.setNome((String) p[1]);
			usuario.setEmail((String) p[2]);
			usuario.setCpf((String) p[3]);

			Perfil perfil = new Perfil();
			perfil.setId(((Integer) p[4]).longValue());
			perfil.setSigla((String) p[5]);
			perfil.setDescricao((String) p[6]);

			usuario.setPerfil(perfil);
			usuario.setAtivo((Boolean) p[7]);

			return usuario;
		}).collect(Collectors.toList());
	}

}
