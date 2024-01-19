-- Disable foreign key checks
SET CONSTRAINTS ALL DEFERRED;

-- Truncate tables
TRUNCATE TABLE cidade CASCADE;
TRUNCATE TABLE cozinha CASCADE;
TRUNCATE TABLE estado CASCADE;
TRUNCATE TABLE forma_pagamento CASCADE;
TRUNCATE TABLE grupo CASCADE;
TRUNCATE TABLE grupo_permissao CASCADE;
TRUNCATE TABLE permissao CASCADE;
TRUNCATE TABLE produto CASCADE;
TRUNCATE TABLE restaurante CASCADE;
TRUNCATE TABLE restaurante_forma_pagamento CASCADE;
TRUNCATE TABLE usuario CASCADE;
TRUNCATE TABLE usuario_grupo CASCADE;

-- Enable foreign key checks
SET CONSTRAINTS ALL IMMEDIATE;

-- Reset auto-increment counters
SELECT pg_catalog.setval(pg_get_serial_sequence('cidade', 'id'), 1, false);
SELECT pg_catalog.setval(pg_get_serial_sequence('cozinha', 'id'), 1, false);
SELECT pg_catalog.setval(pg_get_serial_sequence('estado', 'id'), 1, false);
SELECT pg_catalog.setval(pg_get_serial_sequence('forma_pagamento', 'id'), 1, false);
SELECT pg_catalog.setval(pg_get_serial_sequence('grupo', 'id'), 1, false);
SELECT pg_catalog.setval(pg_get_serial_sequence('permissao', 'id'), 1, false);
SELECT pg_catalog.setval(pg_get_serial_sequence('produto', 'id'), 1, false);
SELECT pg_catalog.setval(pg_get_serial_sequence('restaurante', 'id'), 1, false);
SELECT pg_catalog.setval(pg_get_serial_sequence('usuario', 'id'), 1, false);

-- Insert data into cozinha
INSERT INTO cozinha (id, nome) VALUES (1, 'Tailandesa');
INSERT INTO cozinha (id, nome) VALUES (2, 'Indiana');
INSERT INTO cozinha (id, nome) VALUES (3, 'Argentina');
INSERT INTO cozinha (id, nome) VALUES (4, 'Brasileira');

-- Insert data into estado
INSERT INTO estado (id, nome) VALUES (1, 'Minas Gerais');
INSERT INTO estado (id, nome) VALUES (2, 'São Paulo');
INSERT INTO estado (id, nome) VALUES (3, 'Ceará');

-- Insert data into cidade
INSERT INTO cidade (id, nome_cidade, nome_estado, estado_id) VALUES (1, 'Uberlândia', 'Minas Gerais', 1);
INSERT INTO cidade (id, nome_cidade, nome_estado, estado_id) VALUES (2, 'Belo Horizonte', 'Minas Gerais', 1);
INSERT INTO cidade (id, nome_cidade, nome_estado, estado_id) VALUES (3, 'São Paulo', 'São Paulo', 2);
INSERT INTO cidade (id, nome_cidade, nome_estado, estado_id) VALUES (4, 'Campinas', 'São Paulo', 2);
INSERT INTO cidade (id, nome_cidade, nome_estado, estado_id) VALUES (5, 'Fortaleza', 'Ceará', 3);

-- Insert data into restaurante
INSERT INTO restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro, aberto) VALUES (1, 'Thai Gourmet', 10, 1, current_timestamp, current_timestamp, true, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro', true);
INSERT INTO restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) VALUES (2, 'Thai Delivery', 9.50, 1, current_timestamp, current_timestamp, true, true);
INSERT INTO restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) VALUES (3, 'Tuk Tuk Comida Indiana', 15, 2, current_timestamp, current_timestamp, true, true);
INSERT INTO restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) VALUES (4, 'Java Steakhouse', 12, 3, current_timestamp, current_timestamp, true, true);
INSERT INTO restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) VALUES (5, 'Lanchonete do Tio Sam', 11, 4, current_timestamp, current_timestamp, true, true);
INSERT INTO restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) VALUES (6, 'Bar da Maria', 6, 4, current_timestamp, current_timestamp, true, true);

-- Insert data into forma_pagamento
INSERT INTO forma_pagamento (id, descricao) VALUES (1, 'Cartão de crédito');
INSERT INTO forma_pagamento (id, descricao) VALUES (2, 'Cartão de débito');
INSERT INTO forma_pagamento (id, descricao) VALUES (3, 'Dinheiro');

-- Insert data into permissao
INSERT INTO permissao (id, nome, descricao) VALUES (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
INSERT INTO permissao (id, nome, descricao) VALUES (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');

-- Insert data into restaurante_forma_pagamento
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3), (4, 1), (4, 2), (5, 1), (5, 2), (6, 3);

-- Insert data into produto
INSERT INTO produto (restaurante_id, nome, descricao, preco, ativo) VALUES (1, 'Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, true);
INSERT INTO produto (restaurante_id, nome, descricao, preco, ativo) VALUES (1, 'Camarão tailandês', '16 camarões grandes ao molho picante', 110, true);

INSERT INTO produto (restaurante_id, nome, descricao, preco, ativo) VALUES (2, 'Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, true);

INSERT INTO produto (restaurante_id, nome, descricao, preco, ativo) VALUES (3, 'Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, true);
INSERT INTO produto (restaurante_id, nome, descricao, preco, ativo) VALUES (3, 'Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, true);

INSERT INTO produto (restaurante_id, nome, descricao, preco, ativo) VALUES (4, 'Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, true);
insert into produto (restaurante_id, nome, descricao, preco, ativo) values (4, 'T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, false);

insert into produto (restaurante_id, nome, descricao, preco, ativo) values (5, 'Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, false);

insert into produto (restaurante_id, nome, descricao, preco, ativo) values (6, 'Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, false);

insert into grupo (nome) values ('Gerente'), ('Vendedor'), ('Secretária'), ('Cadastrador');

insert into usuario (id, nome, email, senha, data_cadastro) values (1, 'João da Silva', 'joao.ger@algafood.com', '123', current_timestamp);
insert into usuario (id, nome, email, senha, data_cadastro) values (2, 'Maria Joaquina', 'maria.vnd@algafood.com', '123', current_timestamp);
insert into usuario (id, nome, email, senha, data_cadastro) values (3, 'José Souza', 'jose.aux@algafood.com', '123', current_timestamp);
insert into usuario (id, nome, email, senha, data_cadastro) values (4, 'Sebastião Martins', 'sebastiao.cad@algafood.com', '123', current_timestamp);

insert into grupo_permissao (grupo_id, permissao_id) values (1, 1), (1, 2), (2, 1), (2, 2), (3, 1); 

insert into usuario_grupo (usuario_id, grupo_id) values (1, 1), (1, 2), (2, 2);
