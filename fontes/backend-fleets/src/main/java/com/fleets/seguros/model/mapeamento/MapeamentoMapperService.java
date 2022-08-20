package com.fleets.seguros.model.mapeamento;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import com.fleets.seguros.exception.batch.MapeamentoException;
import com.fleets.seguros.model.Cotacao;

import lombok.extern.slf4j.Slf4j;


/**
 * Classe utilizada no mapeamento do excel
 */
@Slf4j
@Service
public class MapeamentoMapperService {

    /**
     * Converte json em um Registro ajustamento
     *
     * @param json
     * @return
     */
    public Cotacao getRegistroCotacaoFromJson(String json){
        try{
            ObjectMapper mapper = getMapeamentoMapper();
            return mapper.readValue(json, Cotacao.class);
        }catch(IOException e){
            log.error("Erro ao converter Json em Cotação.", e);
            throw new MapeamentoException(e);
        }
    }

    /**
     * Cria e retorna o ObjectMapper utilizado para transformar o json da planilha em objeto
     * @return
     */
    private ObjectMapper getMapeamentoMapper(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.disable(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE);
        mapper.disable(DeserializationFeature.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY);

        mapper.addHandler(new DeserializationProblemHandler() {
            @Override
            public Object handleWeirdStringValue(
                    DeserializationContext ctxt, Class<?> targetType, String valueToConvert, String failureMsg) throws IOException {
                log.error("Erro ao converter campo string. valor=" + valueToConvert);
                return null;
            }

            @Override
            public Object handleWeirdNumberValue(
                    DeserializationContext ctxt, Class<?> targetType, Number valueToConvert, String failureMsg) throws IOException {
                log.error("Erro ao converter campo number. valor=" + valueToConvert);
                return null;
            }
        });
        return mapper;
    }

}


