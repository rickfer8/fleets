INSERT INTO perfil (id, sigla, descricao) SELECT 1, 'ADM', 'ADMINISTRADOR' WHERE NOT EXISTS (SELECT 1 FROM perfil WHERE id = 1 AND sigla = 'ADM');
INSERT INTO perfil (id, sigla, descricao) SELECT 2, 'CRT', 'CORRETOR' WHERE NOT EXISTS (SELECT 2 FROM perfil WHERE id = 2 AND sigla = 'CRT');

INSERT INTO usuario (id, nome, cpf, email, senha, ativo, id_perfil)
  SELECT 1, 'Thiago Aguiar', '97223011521', 'thiagoferreiradeaguiar@gmail.com', '$2a$10$l9A8PP1Kexnn2Ull7Qht.u/LCxX1v83MvWrVA8cQygyxKstOtcIna', true, 1
    WHERE NOT EXISTS (SELECT 1 FROM usuario WHERE id = 1 AND cpf = '97223011521');
   
INSERT INTO usuario (id, nome, cpf, email, senha, ativo, id_perfil)
  SELECT 2, 'Ricardo Ribeiro', '70549889060','ricardo.ferib@gmail.com', '$2a$10$l9A8PP1Kexnn2Ull7Qht.u/LCxX1v83MvWrVA8cQygyxKstOtcIna', true, 1
    WHERE NOT EXISTS (SELECT 2 FROM usuario WHERE id = 2 AND cpf = '70549889060');