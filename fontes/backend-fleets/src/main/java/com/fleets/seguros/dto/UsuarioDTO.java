package com.fleets.seguros.dto;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fleets.seguros.model.Perfil;
import com.fleets.seguros.model.Usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
	
	private Long id;

	@NotBlank(message = "Nome Obrigatório")
	private String nome;

	@Email(message = "E-mail Obrigatório")
	private String email;

	private String senha;
	private String confirmaSenha;

	@Size(min = 1, max = 14, message = "CPF deve estar entre 1 e 14 ")
	private String cpf;

	private Date dataNascimento;

	private boolean ativo;

	@NotNull(message = "Perfil Obrigatorio")
	private PerfilDTO perfil;

	public Usuario mapper() {
		return Usuario.builder().id(id).nome(nome).email(email).senha(senha).cpf(cpf).dataNascimento(dataNascimento)
				.ativo(ativo).perfil(new Perfil(perfil.getId())).build();
	}

}
