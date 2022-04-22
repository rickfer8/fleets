package com.fleets.seguros.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.fleets.seguros.constante.Constante;
import com.fleets.seguros.dto.UsuarioDTO;
import com.fleets.seguros.exception.ExcluiRegistroException;
import com.fleets.seguros.exception.NaoEncontradoException;
import com.fleets.seguros.exception.SenhaUsuarioException;
import com.fleets.seguros.model.Usuario;
import com.fleets.seguros.repository.UsuarioDAO;
import com.fleets.seguros.repository.UsuarioDAOImpl;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioDAO usuarioDAO;

	@Autowired
	private UsuarioDAOImpl usuarioDAOImpl;

	public List<Usuario> findAll() {
		return usuarioDAO.findAll();
	}

	public Usuario getById(Long id) {
		Optional<Usuario> retorno = usuarioDAO.findById(id);
		return retorno.orElseThrow(() -> new NaoEncontradoException(Constante.ERRO_ID_NAO_ENCONTRADO + id));
	}

	public List<Usuario> findByNomeOrEmailOrPerfil(String nome, String email, Integer idPerfil, Boolean ativo) {
		return usuarioDAOImpl.findByNomeOrEmailOrPerfil(nome, email, idPerfil, ativo);
	}

	public Usuario save(UsuarioDTO usuarioDto) {
		String confirmaSenha = usuarioDto.getConfirmaSenha();
		Usuario usuario = usuarioDto.mapper();

		// fluxo de atualizacao
		if (usuarioDto.getId() != null) {
			if (StringUtils.isEmpty(usuario.getSenha()) && StringUtils.isEmpty(confirmaSenha)) {
				Usuario u = getById(usuario.getId());
				usuario.setSenha(u.getSenha());
			} else {
				if (usuario.getSenha().equals(confirmaSenha)) {
					BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
					usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
				} else {
					throw new SenhaUsuarioException(Constante.ERRO_SENHA_USUARIO);
				}
			}
		} else {
			// fluxo de cadastro
			if (!StringUtils.isEmpty(usuario.getSenha()) && usuario.getSenha().equals(confirmaSenha)) {
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
			} else {
				throw new SenhaUsuarioException(Constante.ERRO_SENHA_USUARIO);
			}
		}

		return usuarioDAO.save(usuario);
	}

	public Usuario findByEmail(String email) {
		Optional<Usuario> retorno = usuarioDAO.findByEmail(email);
		return retorno.orElseThrow(() -> new NaoEncontradoException(Constante.ERRO_ID_NAO_ENCONTRADO + email));
	}

	@Transactional
	public void deleteById(Long id) {
		try {
			usuarioDAO.deleteById(id);
		} catch (Exception e) {
			throw new ExcluiRegistroException(Constante.ERRO_EXCLUI_REGISTROS + id);
		}
	}

}
