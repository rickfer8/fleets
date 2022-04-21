package com.fleets.seguros.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fleets.seguros.constante.Constante;
import com.fleets.seguros.exception.ExcluiRegistroException;
import com.fleets.seguros.exception.NaoEncontradoException;
import com.fleets.seguros.model.Usuario;
import com.fleets.seguros.repository.UsuarioDAO;
import com.fleets.seguros.repository.UsuarioDAOImpl;
import com.fleets.seguros.util.HashUtil;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Autowired
	private UsuarioDAOImpl usuarioDAOImpl;
	
	public List<Usuario> findAll(){
		return usuarioDAO.findAll();
	}
	
	public Usuario getById(Long id) {
		Optional<Usuario> retorno = usuarioDAO.findById(id);		
		return retorno.orElseThrow(() -> new NaoEncontradoException(Constante.ERRO_ID_NAO_ENCONTRADO + id ));				
	}
	
	public List<Usuario> findByNomeOrEmailOrPerfil(String nome, String email, Integer idPerfil, Boolean ativo){
		return usuarioDAOImpl.findByNomeOrEmailOrPerfil(nome, email, idPerfil, ativo);
	}
	
	public Usuario save(Usuario usuario) {
		String hash = HashUtil.getSecureHash(usuario.getSenha());
		usuario.setSenha(hash);
		Usuario usuarioNovo = usuarioDAO.save(usuario);
		return usuarioNovo;
	}
	
	public Usuario update(Usuario usuario) {
		String hash = HashUtil.getSecureHash(usuario.getSenha());
		usuario.setSenha(hash);
		Usuario usuarioAtualizado = usuarioDAO.save(usuario);
		return usuarioAtualizado;
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
