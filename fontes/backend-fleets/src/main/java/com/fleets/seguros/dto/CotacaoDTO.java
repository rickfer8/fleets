package com.fleets.seguros.dto;

import java.util.Date;

import com.fleets.seguros.enums.SimNaoEnum;

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
	private Integer codigoFipe;
	private String marca;
	private String modelo;
	private Integer anoFabricacao;
	private Integer anoModelo;
	private String combustivel;
	private SimNaoEnum zeroKm;
	private Integer classeBonus;
	private String cidade;
	private String uf;
	private Double cobertura;
	private Double lmiCasco;
	private Double lmiAcessorios;
	private Double lmiEquipamentos;
	private Double lmiBlindagem;
	private Double lmiKitGas;
	private Double lmiAparelhosPort;
	private Integer vinteQuatroHoras;
	private Integer carroReserva;
	private Integer coberturaVidros;
	private Integer extensaoZeroKm;
	private Double lmiDanosMorais;
	private Double lmiRctrDanosMoraisTerceiros;
	private String rctrClaus;
	private Double lmiAppMorte;
	private Integer tipoFranquia;
	private Double valorFranquiaInformada;
	private Double comissao;
	private Double premioInformadoDanosMoraisTerceiros;
	private String nomeArquivo;

}
