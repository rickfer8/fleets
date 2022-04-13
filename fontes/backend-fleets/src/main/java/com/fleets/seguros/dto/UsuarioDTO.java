package com.fleets.seguros.dto;

import lombok.Data;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fleets.seguros.enuns.PerfilEnum;
import com.fleets.seguros.model.Perfil;
import com.fleets.seguros.model.Usuario;

@Data
public class UsuarioDTO {
	
	@NotBlank(message = "Nome Obrigatório")
	private String nome;
	
	@Email(message = "E-mail Obrigatório")
	private String email;

	@Size(min = 7, max = 64, message = "A senha deve estar entre 4 e 64")
	private String senha;
	
	@Size(min = 1, max = 14, message = "CPF deve estar entre 1 e 14 ")
	private String cpf;	
	
	private Date dataNascimento;
	
	@NotNull(message = "Perfil Obrigatorio")
	private PerfilEnum perfilEnum;	

	public Usuario mapper() {
		return new Usuario().builder()
							.nome(nome)
							.email(email)
							.senha(senha)
							.cpf(cpf)
							.dataNascimento(dataNascimento)
							.ativo(Boolean.TRUE)							
							.build();		
	}


}
