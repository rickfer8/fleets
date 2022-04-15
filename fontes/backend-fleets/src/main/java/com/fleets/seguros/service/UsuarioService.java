package com.fleets.seguros.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fleets.seguros.constante.Constante;
import com.fleets.seguros.exception.NaoEncontradoException;
import com.fleets.seguros.model.Usuario;
import com.fleets.seguros.repository.UsuarioDAO;
import com.fleets.seguros.util.HashUtil;

@Service
public class UsuarioService implements UserDetailsService {
	
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

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Usuario> retorno = usuarioDAO.findByEmail(email);
		
		if(!retorno.isPresent()) throw new UsernameNotFoundException("ERRO_EMAIL_NAO_ENCONTRADO " + email);
		
		Usuario usuario = retorno.get();
		
		List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_" + usuario.getPerfil().getDescricao()));
		
		org.springframework.security.core.userdetails.User usuarioSpring = new org.springframework.security.core.userdetails.User(usuario.getEmail(), usuario.getSenha(), authorities);
		
		return usuarioSpring;
	}
	
	public Usuario findByEmail(String email) {
		Optional<Usuario> retorno = usuarioDAO.findByEmail(email);
		return retorno.orElseThrow(() -> new NaoEncontradoException(Constante.ERRO_ID_NAO_ENCONTRADO + email));
	}

}
