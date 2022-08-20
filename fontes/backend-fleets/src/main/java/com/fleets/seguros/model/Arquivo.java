package com.fleets.seguros.model;

import java.io.InputStream;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fleets.seguros.temporario.ArquivoTemporario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "arquivo")
public class Arquivo implements ArquivoTemporario {
	
	@Id
	@GeneratedValue(generator = "seq_arquivo")
	@Column(name = "id")
	private Long id;
	
	@Column(name = "nome_arquivo")
	private String nomeArquivo;

	@Column(name = "tipo_extensao")
	private String tipoExtensao;
	
	@Column(name = "path_arquivo")
	private String path;
	
	@Column(name = "data_criacao")
	private Date dataCriacao;
	
	@OneToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	@Column(name = "nome_usuario")
	private String nomeUsuario;
	
	@Transient
	private InputStream file;
	

}
