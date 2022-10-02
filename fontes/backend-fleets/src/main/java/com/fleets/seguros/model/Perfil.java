package com.fleets.seguros.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
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
