package com.fleets.seguros.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {

	private Long id;
	private String nome;
	private String email;
	private String senha;
	private String confirmaSenha;
	private String cpf;
	private Date dataNascimento;
	private boolean ativo;
	private PerfilDTO perfil;

}
