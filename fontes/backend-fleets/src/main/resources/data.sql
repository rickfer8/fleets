INSERT INTO perfil (id, sigla, descricao) SELECT 1, 'ADM', 'ADMINISTRADOR' WHERE NOT EXISTS (SELECT 1 FROM perfil WHERE id = 1 AND sigla = 'ADM');

INSERT INTO usuario (id, nome, cpf, data_nascimento, email, senha, ativo, id_perfil)
  SELECT 1, 'Thiago Aguiar', '97223011521', '1985-01-29','thiagoferreiradeaguiar@gmail.com', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', true, 1
    WHERE NOT EXISTS (SELECT 1 FROM usuario WHERE id = 1 AND cpf = '97223011521');
   
INSERT INTO usuario (id, nome, cpf, data_nascimento, email, senha, ativo, id_perfil)
  SELECT 2, 'Ricardo Ribeiro', '70549889060', '1987-02-03','ricardo.ferib@gmail.com', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', true, 1
    WHERE NOT EXISTS (SELECT 2 FROM usuario WHERE id = 2 AND cpf = '70549889060');