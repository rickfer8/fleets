package com.fleets.seguros.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginDTO {
	
	@Email(message = "E-mail Invalido")
	private String email;
	
	@NotBlank(message = "Senha Obrigatório")
	private String password;

}
