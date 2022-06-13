package com.fleets.seguros.converter;

import org.springframework.stereotype.Component;

import com.fleets.seguros.dto.CotacaoDTO;
import com.fleets.seguros.model.Cotacao;

@Component
public class CotacaoConverter {

	public Cotacao convertToEntity(CotacaoDTO dto) {
		Cotacao cotacao = new Cotacao();
		cotacao.setId(dto.getId());
		cotacao.setVigenciaInicial(dto.getVigenciaInicial());
		cotacao.setVigenciaFinal(dto.getVigenciaFinal());
		cotacao.setPlaca(dto.getPlaca());
		cotacao.setChassi(dto.getChassi());
		cotacao.setCodigoFipe(dto.getCodigoFipe());
		cotacao.setMarca(dto.getMarca());
		cotacao.setModelo(dto.getModelo());
		cotacao.setAnoFabricacao(dto.getAnoFabricacao());
		cotacao.setAnoModelo(dto.getAnoModelo());
		cotacao.setCombustivel(dto.getCombustivel());
		cotacao.setNovo(dto.getNovo());
		cotacao.setClasseBonus(dto.getClasseBonus());
		cotacao.setCidade(dto.getCidade());
		cotacao.setUf(dto.getUf());
		cotacao.setCobertura(dto.getCobertura());
		cotacao.setLmiCasco(dto.getLmiCasco());
		cotacao.setLmiAcessorios(dto.getLmiAcessorios());
		cotacao.setLmiEquipamentos(dto.getLmiEquipamentos());
		cotacao.setLmiBlindagem(dto.getLmiBlindagem());
		cotacao.setLmiKitGas(dto.getLmiKitGas());
		cotacao.setLmiAparelhosPort(dto.getLmiAparelhosPort());
		cotacao.setVinteQuatroHoras(dto.getVinteQuatroHoras());
		cotacao.setCarroReserva(dto.getCarroReserva());
		cotacao.setCoberturaVidros(dto.getCoberturaVidros());
		cotacao.setExtensaoNovo(dto.getExtensaoNovo());
		cotacao.setLmiDanosMorais(dto.getLmiDanosMorais());
		cotacao.setLmiRctrDanosMoraisTerceiros(dto.getLmiRctrDanosMoraisTerceiros());
		cotacao.setRctrClaus(dto.getRctrClaus());
		cotacao.setLmiAppMorte(dto.getLmiAppMorte());
		cotacao.setTipoFranquia(dto.getTipoFranquia());
		cotacao.setValorFranquiaInformada(dto.getValorFranquiaInformada());
		cotacao.setComissao(dto.getComissao());
		cotacao.setPremioInformadoDanosMoraisTerceiros(dto.getPremioInformadoDanosMoraisTerceiros());
		return cotacao;
	}

	public CotacaoDTO convertToDto(Cotacao cotacao) {
		CotacaoDTO dto = new CotacaoDTO();
		dto.setId(cotacao.getId());
		dto.setVigenciaInicial(cotacao.getVigenciaInicial());
		dto.setVigenciaFinal(cotacao.getVigenciaFinal());
		dto.setPlaca(cotacao.getPlaca());
		dto.setChassi(cotacao.getChassi());
		dto.setCodigoFipe(cotacao.getCodigoFipe());
		dto.setMarca(cotacao.getMarca());
		dto.setModelo(cotacao.getModelo());
		dto.setAnoFabricacao(cotacao.getAnoFabricacao());
		dto.setAnoModelo(cotacao.getAnoModelo());
		dto.setCombustivel(cotacao.getCombustivel());
		dto.setNovo(cotacao.getNovo());
		dto.setClasseBonus(cotacao.getClasseBonus());
		dto.setCidade(cotacao.getCidade());
		dto.setUf(cotacao.getUf());
		dto.setCobertura(cotacao.getCobertura());
		dto.setLmiCasco(cotacao.getLmiCasco());
		dto.setLmiAcessorios(cotacao.getLmiAcessorios());
		dto.setLmiEquipamentos(cotacao.getLmiEquipamentos());
		dto.setLmiBlindagem(cotacao.getLmiBlindagem());
		dto.setLmiKitGas(cotacao.getLmiKitGas());
		dto.setLmiAparelhosPort(cotacao.getLmiAparelhosPort());
		dto.setVinteQuatroHoras(cotacao.getVinteQuatroHoras());
		dto.setCarroReserva(cotacao.getCarroReserva());
		dto.setCoberturaVidros(cotacao.getCoberturaVidros());
		dto.setExtensaoNovo(cotacao.getExtensaoNovo());
		dto.setLmiDanosMorais(cotacao.getLmiDanosMorais());
		dto.setLmiRctrDanosMoraisTerceiros(cotacao.getLmiRctrDanosMoraisTerceiros());
		dto.setRctrClaus(cotacao.getRctrClaus());
		dto.setLmiAppMorte(cotacao.getLmiAppMorte());
		dto.setTipoFranquia(cotacao.getTipoFranquia());
		dto.setValorFranquiaInformada(cotacao.getValorFranquiaInformada());
		dto.setComissao(cotacao.getComissao());
		dto.setPremioInformadoDanosMoraisTerceiros(cotacao.getPremioInformadoDanosMoraisTerceiros());
		return dto;
	}

}
