package com.fleets.seguros.exception.batch;

/**
 * Classe de exception lançada quando algo imprevisto no mapeamento da linha
 * durante importação da planilha
 */
public class MapeamentoException extends RuntimeException {

    public MapeamentoException(Throwable t){
        super(t);
    }

}
