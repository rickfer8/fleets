package com.fleets.seguros.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;

import com.fleets.seguros.constante.Constante;
import com.fleets.seguros.converter.UsuarioConverter;
import com.fleets.seguros.dto.UsuarioDTO;
import com.fleets.seguros.exception.CadastroRegistroException;
import com.fleets.seguros.exception.ExcluiRegistroException;
import com.fleets.seguros.exception.NaoEncontradoException;
import com.fleets.seguros.exception.SenhaUsuarioException;
import com.fleets.seguros.model.Usuario;
import com.fleets.seguros.repository.UsuarioRepository;
import com.fleets.seguros.repository.UsuarioRepositoryImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class UsuarioService {

	private final UsuarioRepository repository;
	private final UsuarioRepositoryImpl repositoryImpl;
	private final UsuarioConverter converter;

	public List<Usuario> findAll() {
		return repository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
	}

	public Usuario getById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new NaoEncontradoException(Constante.ERRO_ID_NAO_ENCONTRADO + id));
	}

	public List<Usuario> findUsuario(@RequestParam String parametro) {
		return repositoryImpl.findUsuario(parametro);
	}

	public Usuario save(UsuarioDTO dto) {
		log.info("salvando usuario {}", dto);
		try {
			String confirmaSenha = dto.getConfirmaSenha();
			Usuario usuario = converter.convertToEntity(dto);

			// fluxo de atualizacao
			if (dto.getId() != null) {
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
			
			return repository.save(usuario);
		} catch (Exception e) {
			log.error("erro ao salvar usuario {}", dto, e);
			throw new CadastroRegistroException(Constante.ERRO_CADASTRO_REGISTROS);
		}
	}

	public Usuario findByEmail(String email) {
		return repository.findByEmail(email)
				.orElseThrow(() -> new NaoEncontradoException(Constante.ERRO_ID_NAO_ENCONTRADO + email));
	}

	@Transactional
	public void deleteById(Long id) {
		log.info("excluindo usuario {}", id);
		try {
			repository.deleteById(id);
		} catch (Exception e) {
			log.info("erro ao excluir usuario {}", id, e);
			throw new ExcluiRegistroException(Constante.ERRO_EXCLUI_REGISTROS + id);
		}
	}

}
