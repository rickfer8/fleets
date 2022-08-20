package com.fleets.seguros.batch.reader;

import org.json.JSONObject;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * Representa o arquivo mapeamento.json
 *
 */
@Slf4j
public class ArquivoMapeamento {

    private JSONObject root;

	public ArquivoMapeamento(JSONObject root) {
		this.root = root;
	}

    /**
     * Busca os detalhes da coluna contidos no mapeamento.json
     * @param nomeColuna
     * @return
     */
	public ItemMapeamento getItemMapeamento(final String nomeColuna) {
		
		String coluna = nomeColuna.trim().replaceAll(" ", "").toUpperCase();
		
		if (root.has(coluna)) {
			return new ItemMapeamento(coluna, root.getJSONObject(coluna));
		}
		
		log.warn(String.format("Coluna não mapeada: '%s' verificar mapeamento.json.", nomeColuna));
		return null;
	}
}
