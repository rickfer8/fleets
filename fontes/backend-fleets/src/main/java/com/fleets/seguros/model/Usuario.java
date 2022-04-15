package com.fleets.seguros.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fleets.seguros.dto.UsuarioDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
@Entity(name = "usuario")
public class Usuario implements Serializable {
	 
	private static final long serialVersionUID = 5682348415685117787L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 60, nullable = false)
	private String nome;
	
	@Column(length = 14, nullable = false)
	private String cpf;	
	
	@Column(name = "data_nascimento", nullable = false, updatable = false)
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	
	@Getter(onMethod = @__({@JsonIgnore}))
	@Setter(onMethod = @__({@JsonProperty}))	
	@Column(length = 64, nullable = false)
	private String senha;
	
	@Column(length = 60, nullable = false, unique = true)
	private String email;
	
	@Column
	private boolean ativo;
	 
	@ManyToOne
	@JoinColumn(name = "id_perfil", nullable = false)
	private Perfil perfil;
	
	
	public UsuarioDTO mapper() {
		return new UsuarioDTO().builder()
							.nome(nome)
							.email(email)
							.senha(senha)
							.cpf(cpf)
							.dataNascimento(dataNascimento)
							.ativo(Boolean.TRUE)							
							.build();		
	}

}
