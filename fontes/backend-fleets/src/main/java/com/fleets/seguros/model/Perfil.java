package com.fleets.seguros.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity(name = "perfil")
public class Perfil  implements Serializable {
	
	private static final long serialVersionUID = 5564181309426318589L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 10, nullable = false, unique = true)
	private String sigla;
	
	@Column(length = 60, nullable = false)
	private String descricao;
	
	@Getter(onMethod = @__({@JsonIgnore}) )
	@OneToMany(mappedBy = "perfil")
	private List<Usuario> usuarios = new ArrayList<>();

}
