package com.fleets.seguros.service.batch;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fleets.seguros.batch.reader.ArquivoMapeamento;
import com.fleets.seguros.util.FileUtil;


/**
 * Casse responsável por carregar o arquivo de mapeamento
 */
@Service
public class ArquivoMapeamentoService {

    @Autowired
    private FileUtil fileUtil;

    private static final String ARQUIVO_MAPEAMENTO = "mapeamento.json";

    /**
     * Retorna o arquivo de mapeamento das colunas
     *
     * @return
     */
    public ArquivoMapeamento getMapeamento() {
        JSONObject root = new JSONObject(fileUtil.readFromResources(ARQUIVO_MAPEAMENTO));
        return new ArquivoMapeamento(root);
    }

}
