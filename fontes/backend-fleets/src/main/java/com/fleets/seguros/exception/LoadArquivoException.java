package com.fleets.seguros.exception;

/**
 *
 * Classe de exception lançada quando há algo imprevisto na importação de um arquivo
 *
 */
public class LoadArquivoException extends  RuntimeException {

    public LoadArquivoException(Throwable t){
        super(t);
    }

}
