package com.fleets.seguros.constante;

public class Constante {

	public static final String ERRO_ID_NAO_ENCONTRADO = "Não foi encontrado informações com esse Id ";
	public static final String ERRO_DESCRICAO_NAO_ENCONTRADO = "Não foi encontrado informações com essa descrição ";
	public static final String ERRO_EMAIL_NAO_ENCONTRADO = "Não foi encontrado informações com esse e-mail ";
	public static final String ERRO_CAMPOS_INVALIDOS = "Campos Inválidos ";
	public static final String ERRO_EXCLUI_REGISTROS = "Não foi possível excluir o registro ";
	public static final String ERRO_CADASTRO_REGISTROS = "Não foi possível cadastrar o registro";
	public static final String ERRO_SENHA_USUARIO = "Campo senha e confirma senha estão diferentes";

	public static final int JWT_EXP_DAYS = 1;
	public static final String API_KEY = "fleets-seguros";
	public static final String JWT_PROVIDER = "Bearer";
	public static final String JWT_ROLE_KEY = "role";
	public static final String JWT_MSG_INVALIDO = "Token Inválido";
	
	/** PASTA_TEMPORARIA_ARQUIVO. */
	public static final String PASTA_TEMPORARIA_ARQUIVO = "fleets-seguros";	
	
	/** MENSAGENS PADRÃO */
	public static final String LOG_UPLOAD_COLUNA_AND_LINHA = "Coluna: %s, Linha(%d): %s";
	
	public static final String ERRO_NOT_FOUND = "não mapeada para a apolice. Verifique a planilha importada e faça as correções necessárias.";
	
	public static final String ITEM_MAPEAMENTO_VAZIO = "Existe uma coluna com cabeçalho vazio. "
			+ "Verifique a planilha importada e faça as correções necessárias.";	
	
	public static final String LOG_UPLOAD_DADO_TIPO_INVALIDO = "Tipo informado diverge do tipo esperado na tabela de configuração do banco de dados.";
	public static final String LOG_UPLOAD_DADO_TAMANHO = "Tamanho do campo excede o limite esperado na tabela de configuração do banco de dados.";
	
	/** NOMES DE COLUNA DA IMPORTAÇÃO DE PLANILHA */
    public static final String COLUNA_CIDADE = "CIDADE";
    public static final String COLUNA_COMBUSTIVEL = "COMBUSTIVEL";
    public static final String COLUNA_VIGENCIA_FINAL = "VIGÊNCIA FINAL";
    public static final String COLUNA_PLACA = "PLACA";
    public static final String COLUNA_TIPO_ENDOSSO = "TIPO ENDOSSO";
    public static final String COLUNA_MOTIVO = "MOTIVO";
    public static final String COLUNA_ITEM = "ITEM";

}
