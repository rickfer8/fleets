package com.fleets.seguros.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "perfil")
public class Perfil {

	@Id
	@GeneratedValue(generator = "seq_perfil")
	@Column(name = "id")
	private Long id;

	@Column(name = "sigla")
	private String sigla;

	@Column(name = "descricao")
	private String descricao;

}
