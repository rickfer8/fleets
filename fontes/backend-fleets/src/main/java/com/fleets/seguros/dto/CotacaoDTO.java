package com.fleets.seguros.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CotacaoDTO {

	private Long id;
	private Date vigenciaInicial;
	private Date vigenciaFinal;
	private String placa;
	private String chassi;
	private String codigoFipe;
	private String marca;
	private String modelo;
	private Date anoFabricacao;
	private Date anoModelo;
	private String combustivel;
	private Boolean novo;
	private String classeBonus;
	private String cidade;
	private String uf;
	private Integer cobertura;
	private String lmiCasco;
	private String lmiAcessorios;
	private String lmiEquipamentos;
	private String lmiBlindagem;
	private String lmiKitGas;
	private String lmiAparelhosPort;
	private Integer vinteQuatroHoras;
	private Integer carroReserva;
	private Integer coberturaVidros;
	private Integer extensaoNovo;
	private Double lmiDanosMorais;
	private Double lmiRctrDanosMoraisTerceiros;
	private Integer rctrClaus;
	private Double lmiAppMorte;
	private Integer tipoFranquia;
	private Double valorFranquiaInformada;
	private Integer comissao;
	private Double premioInformadoDanosMoraisTerceiros;

}
