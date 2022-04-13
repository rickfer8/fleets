package com.fleets.seguros.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fleets.seguros.constante.Constante;
import com.fleets.seguros.exception.NaoEncontradoException;
import com.fleets.seguros.model.Usuario;
import com.fleets.seguros.repository.UsuarioDAO;
import com.fleets.seguros.util.HashUtil;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
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
	
	public Usuario getById(Long id) {
		Optional<Usuario> retorno = usuarioDAO.findById(id);		
		return retorno.orElseThrow(() -> new NaoEncontradoException(Constante.ERRO_ID_NAO_ENCONTRADO + id ));				
	}
	
	public List<Usuario> listAll(){
		List<Usuario> usuarios = usuarioDAO.findAll();
		return usuarios;
	}
	
	public Usuario login(String email, String senha) {
		senha = HashUtil.getSecureHash(senha);
		Optional<Usuario> retorno = usuarioDAO.login(email, senha);
		return retorno.get();
	}

}
