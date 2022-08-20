package com.fleets.seguros.util;

import com.fleets.seguros.exception.LoadArquivoException;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
/**
 * Classe responsável em carregar arquivos contidos na pasta resources.
 */
public class FileUtil {

    public String readFromResources(final String fileName){
        try {
            return Resources.toString(Resources.getResource(fileName), Charsets.UTF_8);
        } catch (IOException e) {
            throw new LoadArquivoException(e);
        }
    }
}



