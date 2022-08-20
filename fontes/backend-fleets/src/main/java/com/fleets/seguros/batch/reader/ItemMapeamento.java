package com.fleets.seguros.batch.reader;



import lombok.*;
import org.apache.poi.ss.usermodel.Cell;
import org.json.JSONObject;

import com.fleets.seguros.enums.ItemMapeamentoType;
import com.fleets.seguros.util.DateUtil;

/**
 * Um item de mapeamento contido no mapeamento.json
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemMapeamento {

    private String coluna;
    private String valor;
    private boolean required;
    private boolean ignore;
    private String converter;
    private ItemMapeamentoType type;
    private String[] valorArray;
    private String size;

    /**
     * Construtor utilizado durante a importação da planilha
     *
     * @param coluna
     * @param json
     */
    public ItemMapeamento(String coluna, JSONObject json) {
        this.coluna = coluna;
        this.valor = json.getString("value");
        this.required = json.getBoolean("required");
        this.ignore = json.has("ignore") ? json.getBoolean("ignore") : false;
        this.converter = json.has("converter") ? json.getString("converter") : null;
        this.type = ItemMapeamentoType.valueOf(json.getString("type").toUpperCase());
        this.valorArray = valor.split("[.]");
        this.size = json.has("size") ? json.getString("size") : null;
    }

    public Object lerCelula(Cell cell) {
        Object content = null;
        String conteudoCelula = cell.toString().trim();
        
        switch (getType()) {
            case DATE:
                content = DateUtil.formataDataPadraoISO(conteudoCelula);
                break;
            case NUMBER:
                content = corrigeNumerosEmCellString(conteudoCelula);
                break;
            case BOOLEAN:
                content = new Boolean(conteudoCelula);
                break;
            case STRING:
            default:
                if ("S".equalsIgnoreCase(conteudoCelula) || "N".equalsIgnoreCase(conteudoCelula)) {
                    content = conteudoCelula.toUpperCase();
                } else {
                    content = conteudoCelula;
                }
                break;
        }
        return content;
    }

    /**
     * Corrige números subistituindo virgula por ponto.
     *
     * @param s
     * @return
     */
    private String corrigeNumerosEmCellString(String s) {
        return s.replaceAll(",", ".");
    }

}
