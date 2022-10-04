package com.fleets.seguros.util;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Response {

    /**
    * Código de status da resposta
    */
	private Integer code;

    /**
    * Mensagem de resposta
    */
	private String message;

}
