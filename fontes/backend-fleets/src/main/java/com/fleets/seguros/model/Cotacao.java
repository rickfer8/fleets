
package com.fleets.seguros.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fleets.seguros.enums.SimNaoEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cotacao")
public class Cotacao implements Serializable {

	private static final long serialVersionUID = 3064961173558242729L;

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
	private Integer codigoFipe;

	@Column(name = "marca")
	private String marca;

	@Column(name = "modelo")
	private String modelo;

	@Column(name = "ano_fabricacao")
	private Integer anoFabricacao;

	@Column(name = "ano_modelo")
	private Integer anoModelo;

	@Column(name = "combustivel")
	private String combustivel;

	@Column(name = "zeroKm")
	@Enumerated(EnumType.STRING)
	private SimNaoEnum zeroKm;

	@Column(name = "classe_bonus")
	private Integer classeBonus;

	@Column(name = "cidade")
	private String cidade;

	@Column(name = "uf")
	private String uf;

	@Column(name = "cobertura")
	private Double cobertura;

	@Column(name = "lmi_casco")
	private Double lmiCasco;

	@Column(name = "lmi_acessorios")
	private Double lmiAcessorios;

	@Column(name = "lmi_equipamentos")
	private Double lmiEquipamentos;

	@Column(name = "lmi_blindagem")
	private Double lmiBlindagem;

	@Column(name = "lmi_kit_gas")
	private Double lmiKitGas;

	@Column(name = "lmi_aparelhos_port")
	private Double lmiAparelhosPort;

	@Column(name = "vinte_quatro_horas")
	private Integer vinteQuatroHoras;

	@Column(name = "carro_reserva")
	private Integer carroReserva;

	@Column(name = "cobertura_vidros")
	private Integer coberturaVidros;

	@Column(name = "extensao_zero_Km")
	private Integer extensaoZeroKm;

	@Column(name = "lmi_danos_morais")
	private Double lmiDanosMorais;

	@Column(name = "lmi_rctr_danos_morais_terceiros")
	private Double lmiRctrDanosMoraisTerceiros;

	@Column(name = "rctr_claus_112")
	private String rctrClaus;

	@Column(name = "lmi_app_morte")
	private Double lmiAppMorte;

	@Column(name = "tipo_franquia")
	private Integer tipoFranquia;

	@Column(name = "valor_franquia_informada")
	private Double valorFranquiaInformada;

	@Column(name = "comissao")
	private Double comissao;

	@Column(name = "premio_informado_rctr_danos_morais_terceiros")
	private Double premioInformadoDanosMoraisTerceiros;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_apolice", nullable=false)
	private Apolice apolice;	

}
