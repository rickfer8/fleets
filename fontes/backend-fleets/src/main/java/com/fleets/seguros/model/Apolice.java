package com.fleets.seguros.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fleets.seguros.enums.AtivoInativoEnum;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@Table(name = "arquivo")
public class Apolice {
	
	@Id
	@GeneratedValue(generator = "seq_apolice")
	@Column(name = "id")
	private Long id;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "data_criacao")
	private Date dataCriacao;
	
	@OneToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	@OneToOne
	@JoinColumn(name = "id_arquivo")
	private Arquivo arquivo;
	
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private AtivoInativoEnum status;

}
