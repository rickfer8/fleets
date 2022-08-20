package com.fleets.seguros.model.batch;



import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fleets.seguros.constante.Constante;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
* Classe responsavel por mapear a tabela TB_UPLOAD_LOG_ERROS 
* que armazena as validações
*/

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@Table(name="upload_log_erro")
public class UploadLogErro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "GEN_UPLOAD_LOG_ERROS_ID")
    @SequenceGenerator(name="GEN_UPLOAD_LOG_ERROS_ID", sequenceName="sysaut.SQ_UPLOAD_LOG_ERROS", allocationSize=1)
    private Long id;

    @Column(name = "job_execution_id")
    private BigDecimal jobExecutionId;

    @Column(name = "num_linha")
    private Integer linha;
    
    @Column(name = "titulo_coluna")
    private String tituloColuna;
    
    @Column(name = "mensagem_erro")
    private String mensagemErro;
    
    public UploadLogErro() { }
    
	/**
	 * Construtor utilizado para criar um novo UploadLogErro e ja montar a mensagem padrão
	 * Existe uma mensagem que não utiliza o prefixo padrão, ela é identificada pelo numeroLinha nulo
	 * 
	 * @param numeroLinha
	 * @param nomeColuna
	 * @param mensagemErro
	 */
	public UploadLogErro(Integer numeroLinha, String nomeColuna, String mensagemErro) {
		super();
		this.linha = numeroLinha;
		this.tituloColuna = nomeColuna;
		
		if (numeroLinha == null) {
			this.mensagemErro = mensagemErro;
		} else {
			this.mensagemErro = montarMensagemErro(nomeColuna, numeroLinha, mensagemErro);
		}
	}
    
	private String montarMensagemErro(String nomeColuna, Integer numeroLinha, String mensagemErro) {
		return String.format(Constante.LOG_UPLOAD_COLUNA_AND_LINHA, nomeColuna, numeroLinha, mensagemErro);
	}
}