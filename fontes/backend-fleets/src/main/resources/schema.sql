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