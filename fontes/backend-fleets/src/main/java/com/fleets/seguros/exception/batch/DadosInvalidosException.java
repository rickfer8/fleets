package com.fleets.seguros.exception.batch;


import lombok.Getter;

import java.util.Collections;
import java.util.List;

import com.fleets.seguros.model.batch.UploadLogErro;

import static com.fleets.seguros.util.MapeamentoValidatorUtil.criarLog;

/**
 * Excepction para armazenar os logs das validações
 * realizadas na leitura da planilha
 *
 * Armazena uma lista de logs
 */

@Getter
public class DadosInvalidosException extends RuntimeException {

    private static final long serialVersionUID = 3200805442744421656L;
    private final List<UploadLogErro> logs;

    public DadosInvalidosException(List<UploadLogErro> logs) {
        this.logs = logs;
    }

    public DadosInvalidosException(String message) {
        this.logs = Collections.singletonList(criarLog(null, null, message, null));
    }
}
