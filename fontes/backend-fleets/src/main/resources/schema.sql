-- Table: perfil

-- DROP TABLE perfil;

CREATE TABLE IF NOT EXISTS perfil
(
  id integer NOT NULL,
  sigla character varying(10),
  descricao character varying(60),
  CONSTRAINT pk_perfil PRIMARY KEY (id),
  CONSTRAINT un_perfil UNIQUE (sigla)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE perfil OWNER TO postgres;
  
-- Table: usuario

-- DROP TABLE usuario;

CREATE TABLE IF NOT EXISTS usuario
(
  id integer NOT NULL,
  nome character varying(60),
  cpf character varying(14),  
  email character varying(60),
  senha character varying(64),
  ativo boolean,
  id_perfil integer NOT NULL,
  CONSTRAINT pk_usuario PRIMARY KEY (id),
  CONSTRAINT un_usuario UNIQUE (email),
  CONSTRAINT fk_usuario_perfil FOREIGN KEY (id_perfil) REFERENCES perfil(id)
)
WITH (
  OIDS=FALSE
);

ALTER TABLE usuario OWNER TO postgres; 

-- Table: arquivo

-- DROP TABLE arquivo;

CREATE TABLE IF NOT EXISTS arquivo
(
  id integer NOT NULL,
  nome_arquivo character varying(255),
  tipo_extensao character varying(10),
  path_arquivo character varying(255),
  data_criacao date,
  id_usuario integer NOT NULL,
  nome_usuario character varying(60),  
  CONSTRAINT pk_arquivo PRIMARY KEY (id),
  CONSTRAINT fk_arquivo_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id) 
)
WITH (
  OIDS=FALSE
);

ALTER TABLE arquivo OWNER TO postgres; 

-- Table: apolice

-- DROP TABLE apolice;

CREATE TABLE IF NOT EXISTS apolice
(
  id integer NOT NULL,
  descricao character varying(255),
  id_usuario integer NOT NULL,
  id_arquivo integer NOT NULL,
  data_criacao date,
  status character varying(1),    
  CONSTRAINT pk_apolice PRIMARY KEY (id),
  CONSTRAINT fk_apolice_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id),
  CONSTRAINT fk_apolice_arquivo FOREIGN KEY (id_arquivo) REFERENCES arquivo(id) 
)
WITH (
  OIDS=FALSE
);

ALTER TABLE apolice OWNER TO postgres; 

-- Table: upload_log_erro

-- DROP TABLE upload_log_erro;

CREATE TABLE IF NOT EXISTS upload_log_erro
(
  id integer NOT NULL,
  job_execution_id integer,
  num_linha integer,
  titulo_coluna character varying(100),
  mensagem_erro character varying(255),     
  CONSTRAINT pk_upload_log_erros PRIMARY KEY (id)  
)
WITH (
  OIDS=FALSE
);

ALTER TABLE upload_log_erro OWNER TO postgres;

-- DROP TABLE public.batch_job_excution_data;

CREATE TABLE IF NOT EXISTS batch_job_excution_data (
	id int8 NOT NULL,
	current_count int8 NULL,
	free_memory int8 NULL,
	job_execution_id int8 NULL,
	max_memory int8 NULL,
	total_count int8 NULL,
	total_memory int8 NULL,
	CONSTRAINT pk_batch_job_excution_data PRIMARY KEY (id)
);

ALTER TABLE batch_job_excution_data OWNER TO postgres;

-- public.cotacao definition

-- Drop table

-- DROP TABLE public.cotacao;

CREATE TABLE IF NOT EXISTS cotacao (
	id int8 NOT NULL,
	ano_fabricacao int4 NULL,
	ano_modelo int4 NULL,
	carro_reserva int4 NULL,
	chassi varchar(255) NULL,
	cidade varchar(255) NULL,
	classe_bonus int4 NULL,
	cobertura float8 NULL,
	cobertura_vidros int4 NULL,
	codigo_fipe int4 NULL,
	combustivel varchar(255) NULL,
	comissao float8 NULL,
	extensao_zero_Km int4 NULL,
	lmi_acessorios float8 NULL,
	lmi_aparelhos_port float8 NULL,
	lmi_app_morte float8 NULL,
	lmi_blindagem float8 NULL,
	lmi_casco float8 NULL,
	lmi_danos_morais float8 NULL,
	lmi_equipamentos float8 NULL,
	lmi_kit_gas float8 NULL,
	lmi_rctr_danos_morais_terceiros float8 NULL,
	marca varchar(255) NULL,
	modelo varchar(255) NULL,
	zero_km varchar(1) NULL,
	placa varchar(255) NULL,
	premio_informado_rctr_danos_morais_terceiros float8 NULL,
	rctr_claus_112 varchar(255) NULL,
	tipo_franquia int4 NULL,
	uf varchar(255) NULL,
	valor_franquia_informada float8 NULL,
	vigencia_final timestamp NULL,
	vigencia_inicial timestamp NULL,
	vinte_quatro_horas int4 NULL,
	id_apolice int8 NULL,
	CONSTRAINT pk_cotacao PRIMARY KEY (id),
	CONSTRAINT fk_cotacao_apolice FOREIGN KEY (id_apolice) REFERENCES apolice(id)
);

ALTER TABLE cotacao OWNER TO postgres;

-- public.batch_job_instance definition

-- Drop table

-- DROP TABLE public.batch_job_instance;

CREATE TABLE IF NOT EXISTS batch_job_instance (
	job_instance_id int8 NOT NULL,
	"version" int8 NULL,
	job_name varchar(100) NOT NULL,
	job_key varchar(32) NOT NULL,
	CONSTRAINT batch_job_instance_pkey PRIMARY KEY (job_instance_id),
	CONSTRAINT job_inst_un UNIQUE (job_name, job_key)
);

ALTER TABLE batch_job_instance OWNER TO postgres;

-- public.batch_job_execution definition

-- Drop table

-- DROP TABLE public.batch_job_execution;

CREATE TABLE IF NOT EXISTS batch_job_execution (
	job_execution_id int8 NOT NULL,
	"version" int8 NULL,
	job_instance_id int8 NOT NULL,
	create_time timestamp NOT NULL,
	start_time timestamp NULL,
	end_time timestamp NULL,
	status varchar(10) NULL,
	exit_code varchar(2500) NULL,
	exit_message varchar(2500) NULL,
	last_updated timestamp NULL,
	job_configuration_location varchar(2500) NULL,
	CONSTRAINT batch_job_execution_pkey PRIMARY KEY (job_execution_id),
	CONSTRAINT job_inst_exec_fk FOREIGN KEY (job_instance_id) REFERENCES batch_job_instance(job_instance_id)	
);

ALTER TABLE batch_job_execution OWNER TO postgres;

-- public.batch_step_execution definition

-- Drop table

-- DROP TABLE public.batch_step_execution;

CREATE TABLE IF NOT EXISTS batch_step_execution (
	step_execution_id int8 NOT NULL,
	"version" int8 NOT NULL,
	step_name varchar(100) NOT NULL,
	job_execution_id int8 NOT NULL,
	start_time timestamp NOT NULL,
	end_time timestamp NULL,
	status varchar(10) NULL,
	commit_count int8 NULL,
	read_count int8 NULL,
	filter_count int8 NULL,
	write_count int8 NULL,
	read_skip_count int8 NULL,
	write_skip_count int8 NULL,
	process_skip_count int8 NULL,
	rollback_count int8 NULL,
	exit_code varchar(2500) NULL,
	exit_message varchar(2500) NULL,
	last_updated timestamp NULL,
	CONSTRAINT batch_step_execution_pkey PRIMARY KEY (step_execution_id),
	CONSTRAINT job_exec_step_fk FOREIGN KEY (job_execution_id) REFERENCES batch_job_execution(job_execution_id)	
);

ALTER TABLE batch_step_execution OWNER TO postgres;

-- public.batch_job_execution_context definition

-- Drop table

-- DROP TABLE public.batch_job_execution_context;

CREATE TABLE IF NOT EXISTS batch_job_execution_context (
	job_execution_id int8 NOT NULL,
	short_context varchar(2500) NOT NULL,
	serialized_context text NULL,
	CONSTRAINT batch_job_execution_context_pkey PRIMARY KEY (job_execution_id),
	CONSTRAINT job_exec_ctx_fk FOREIGN KEY (job_execution_id) REFERENCES batch_job_execution(job_execution_id)	
);

ALTER TABLE batch_job_execution_context OWNER TO postgres;

-- public.batch_job_execution_params definition

-- Drop table

-- DROP TABLE public.batch_job_execution_params;

CREATE TABLE IF NOT EXISTS batch_job_execution_params (
	job_execution_id int8 NOT NULL,
	type_cd varchar(6) NOT NULL,
	key_name varchar(100) NOT NULL,
	string_val varchar(250) NULL,
	date_val timestamp NULL,
	long_val int8 NULL,
	double_val float8 NULL,
	identifying bpchar(1) NOT NULL,
	CONSTRAINT job_exec_params_fk FOREIGN KEY (job_execution_id) REFERENCES batch_job_execution(job_execution_id)	
);

-- public.batch_step_execution_context definition

-- Drop table

-- DROP TABLE public.batch_step_execution_context;

CREATE TABLE IF NOT EXISTS batch_step_execution_context (
	step_execution_id int8 NOT NULL,
	short_context varchar(2500) NOT NULL,
	serialized_context text NULL,
	CONSTRAINT batch_step_execution_context_pkey PRIMARY KEY (step_execution_id),
	CONSTRAINT step_exec_ctx_fk FOREIGN KEY (step_execution_id) REFERENCES batch_step_execution(step_execution_id)
);

ALTER TABLE batch_step_execution_context OWNER TO postgres;



