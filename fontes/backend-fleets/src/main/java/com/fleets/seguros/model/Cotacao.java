package com.fleets.seguros.model;

import java.util.Date;

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
@Table(name = "cotacao")
public class Cotacao {

	@Id
	@GeneratedValue(generator = "seq_cotacao")
	@Column(name = "id")
	private Long id;

	@Column(name = "vigencia_inicial")
	private Date vigenciaInicial;

	@Column(name = "vigencia_final")
	private Date vigenciaFinal;

	@Column(name = "placa")
	private String placa;

	@Column(name = "chassi")
	private String chassi;

	@Column(name = "codigo_fipe")
	private String codigoFipe;

	@Column(name = "marca")
	private String marca;

	@Column(name = "modelo")
	private String modelo;

	@Column(name = "ano_fabricacao")
	private Date anoFabricacao;

	@Column(name = "ano_modelo")
	private Date anoModelo;

	@Column(name = "combustivel")
	private String combustivel;

	@Column(name = "novo")
	private Boolean novo;

	@Column(name = "classe_bonus")
	private String classeBonus;

	@Column(name = "cidade")
	private String cidade;

	@Column(name = "uf")
	private String uf;

	@Column(name = "cobertura")
	private Integer cobertura;

	@Column(name = "lmi_casco")
	private String lmiCasco;

	@Column(name = "lmi_acessorios")
	private String lmiAcessorios;

	@Column(name = "lmi_equipamentos")
	private String lmiEquipamentos;

	@Column(name = "lmi_blindagem")
	private String lmiBlindagem;

	@Column(name = "lmi_kit_gas")
	private String lmiKitGas;

	@Column(name = "lmi_aparelhos_port")
	private String lmiAparelhosPort;

	@Column(name = "vinte_quatro_horas")
	private Integer vinteQuatroHoras;

	@Column(name = "carro_reserva")
	private Integer carroReserva;

	@Column(name = "cobertura_vidros")
	private Integer coberturaVidros;

	@Column(name = "extensao_novo")
	private Integer extensaoNovo;

	@Column(name = "lmi_danos_morais")
	private Double lmiDanosMorais;

	@Column(name = "lmi_rctr_danos_morais_terceiros")
	private Double lmiRctrDanosMoraisTerceiros;

	@Column(name = "rctr_claus_112")
	private Integer rctrClaus;

	@Column(name = "lmi_app_morte")
	private Double lmiAppMorte;

	@Column(name = "tipo_franquia")
	private Integer tipoFranquia;

	@Column(name = "valor_franquia_informada")
	private Double valorFranquiaInformada;

	@Column(name = "comissao")
	private Integer comissao;

	@Column(name = "premio_informado_rctr_danos_morais_terceiros")
	private Double premioInformadoDanosMoraisTerceiros;

}
