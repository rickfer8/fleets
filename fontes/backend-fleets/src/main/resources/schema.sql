-- Table: perfil

-- DROP TABLE perfil;

CREATE TABLE IF NOT EXISTS perfil
(
  id integer NOT NULL,
  sigla character varying(10),
  descricao character varying(60),
  CONSTRAINT pk_perfil PRIMARY KEY (id ),
  CONSTRAINT un_perfil UNIQUE (sigla )
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
  data_nascimento date,
  email character varying(60),
  senha character varying(64),
  ativo boolean,
  id_perfil integer NOT NULL,
  CONSTRAINT pk_usuario PRIMARY KEY (id ),
  CONSTRAINT un_usuario UNIQUE (email )
)
WITH (
  OIDS=FALSE
);

ALTER TABLE usuario DROP CONSTRAINT IF EXISTS fk_usuario_perfil;
ALTER TABLE usuario ADD CONSTRAINT fk_usuario_perfil FOREIGN KEY (id_perfil) REFERENCES perfil(id);

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
  CONSTRAINT pk_arquivo PRIMARY KEY (id )  
)
WITH (
  OIDS=FALSE
);

ALTER TABLE arquivo DROP CONSTRAINT IF EXISTS fk_arquivo_usuario;
ALTER TABLE arquivo ADD CONSTRAINT fk_arquivo_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id);

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
  CONSTRAINT pk_apolice PRIMARY KEY (id )  
)
WITH (
  OIDS=FALSE
);

ALTER TABLE apolice DROP CONSTRAINT IF EXISTS fk_apolice_usuario;
ALTER TABLE apolice ADD CONSTRAINT fk_apolice_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id);
ALTER TABLE apolice DROP CONSTRAINT IF EXISTS fk_apolice_arquivo;
ALTER TABLE apolice ADD CONSTRAINT fk_apolice_arquivo FOREIGN KEY (id_arquivo) REFERENCES arquivo(id);

ALTER TABLE apolice OWNER TO postgres; 

-- Table: apolice

-- DROP TABLE apolice;

CREATE TABLE IF NOT EXISTS upload_log_erro
(
  id integer NOT NULL,
  job_execution_id integer,
  num_linha integer,
  titulo_coluna character varying(100),
  mensagem_erro character varying(255),     
  CONSTRAINT pk_upload_log_erros PRIMARY KEY (id )  
)
WITH (
  OIDS=FALSE
);