package com.fleets.seguros.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fleets.seguros.dto.PerfilDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "perfil")
public class Perfil implements Serializable {

	private static final long serialVersionUID = 5564181309426318589L;

	@Id
	@GeneratedValue(generator = "seq_perfil")
	private Long id;

	@Column(length = 10, nullable = false, unique = true)
	private String sigla;

	@Column(length = 60, nullable = false)
	private String descricao;

	@Getter(onMethod = @__({ @JsonIgnore }))
	@OneToMany(mappedBy = "perfil")
	private List<Usuario> usuarios = new ArrayList<>();

	public PerfilDTO mapper() {
		new PerfilDTO();
		return PerfilDTO
				.builder()
				.id(id)
				.sigla(sigla)
				.descricao(descricao)
				.build();
	}

}
