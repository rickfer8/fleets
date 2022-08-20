package com.fleets.seguros.util;



import java.math.BigDecimal;

import com.fleets.seguros.model.batch.UploadLogErro;

import static java.lang.Float.parseFloat;
import static java.math.BigDecimal.ZERO;

/**
 * Classe com métodos utilitários que podem ser utilizados para as classes
 * validadoras no processo de Upload
 */
public class MapeamentoValidatorUtil {
    private MapeamentoValidatorUtil() { }

    /**
     * Retorna a mensagem de erro formatada
     *
     * @param coluna
     * @param numeroLinha
     * @param mensagem
     * @return
     */
    public static String getMensagemErro(final String coluna, final int numeroLinha, final String mensagem) {
        return String.format("Coluna: %s, Linha(%d): %s", coluna, numeroLinha, mensagem);
    }

    /**
     * Método que monta o log do tipo UploadLogErro com os parâmetros passados por parâmetro
     * @param numeroLinha
     * @param nomeColuna
     * @param mensagemErro
     * @param jobExecutionId
     * @return
     */
    public static UploadLogErro criarLog(Integer numeroLinha, String nomeColuna, String mensagemErro, BigDecimal jobExecutionId) {
        UploadLogErro uploadLogErro = new UploadLogErro();
        uploadLogErro.setJobExecutionId(jobExecutionId);
        uploadLogErro.setLinha(numeroLinha);
        uploadLogErro.setMensagemErro(mensagemErro);
        uploadLogErro.setTituloColuna(nomeColuna);
        return uploadLogErro;
    }
    
    public static UploadLogErro criarLog(Integer numeroLinha, String nomeColuna, String mensagemErro) {
        UploadLogErro uploadLogErro = new UploadLogErro();
        
        uploadLogErro.setLinha(numeroLinha);
        uploadLogErro.setMensagemErro(mensagemErro);
        uploadLogErro.setTituloColuna(nomeColuna);
        
        return uploadLogErro;
    }

    /**
     * Retorna um número válido de acordo com o valor passado
     * @param valor
     * @return
     */
    public static Integer getValidNumber(String valor) {
        if (valor != null && !valor.isEmpty()) {
            try {
                return (int) parseFloat(valor);
            } catch (NumberFormatException e) {
                return ZERO.intValue();
            }
        }
        return ZERO.intValue();
    }
}
