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
import org.springframework.web.bind.annotation.RequestParam;

import com.fleets.seguros.model.Perfil;
import com.fleets.seguros.model.Usuario;

@Repository
public class UsuarioRepositoryImpl {

	@PersistenceContext
	private EntityManager em;

	public List<Usuario> findUsuario(@RequestParam String parametro) {
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

		Query query = em.createNativeQuery(sql.toString(), Tuple.class);
		queryParams.forEach(query::setParameter);

		List<Tuple> usuarios = query.getResultList();
		return usuarios.stream().map(tupla -> {
			Usuario usuario = new Usuario();
			usuario.setId(tupla.get("id", Integer.class).longValue());
			usuario.setNome(tupla.get("nome", String.class));
			usuario.setEmail(tupla.get("email", String.class));
			usuario.setCpf(tupla.get("cpf", String.class));
			usuario.setAtivo(tupla.get("ativo", Boolean.class));

			Perfil perfil = new Perfil();
			perfil.setId(tupla.get("idPerfil", Integer.class).longValue());
			perfil.setSigla(tupla.get("sigla", String.class));
			perfil.setDescricao(tupla.get("descricao", String.class));
			usuario.setPerfil(perfil);
			
			return usuario;
		}).collect(Collectors.toList());
	}

}
