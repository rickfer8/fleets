package com.fleets.seguros.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(generator = "seq_usuario")
	@Column(name = "id")
	private Long id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "cpf")
	private String cpf;

	@Column(name = "senha")
	private String senha;

	@Column(name = "email")
	private String email;

	@Column(name = "ativo")
	private boolean ativo;

	@OneToOne
	@JoinColumn(name = "id_perfil")
	private Perfil perfil;

}
