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
TRUNCATE TABLE restaurante_usuario_responsavel CASCADE;
TRUNCATE TABLE usuario CASCADE;
TRUNCATE TABLE usuario_grupo CASCADE;
TRUNCATE TABLE pedido CASCADE;
TRUNCATE TABLE item_pedido CASCADE;
TRUNCATE TABLE foto_produto CASCADE;

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

--insert into usuario (id, nome, email, senha, data_cadastro) values (1, 'João da Silva', 'joao.ger@algafood.com', '123', current_timestamp);
insert into usuario (id, nome, email, senha, data_cadastro) values (1, 'João da Silva', 'lluanps@gmail.com', '123', current_timestamp);
insert into usuario (id, nome, email, senha, data_cadastro) values (2, 'Maria Joaquina', 'maria.vnd@algafood.com', '123', current_timestamp);
insert into usuario (id, nome, email, senha, data_cadastro) values (3, 'José Souza', 'jose.aux@algafood.com', '123', current_timestamp);
insert into usuario (id, nome, email, senha, data_cadastro) values (4, 'Sebastião Martins', 'sebastiao.cad@algafood.com', '123', current_timestamp);
insert into usuario (id, nome, email, senha, data_cadastro) values (5, 'Luan Pinheiro', 'luan@gmail.com', 'admin', current_timestamp);

insert into grupo_permissao (grupo_id, permissao_id) values (1, 1), (1, 2), (2, 1), (2, 2), (3, 1); 

insert into usuario_grupo (usuario_id, grupo_id) values (1, 1), (1, 2), (2, 2);

insert into restaurante_usuario_responsavel (restaurante_id, usuario_id) values (1, 5), (3, 5);

insert into pedido (id, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, 
                    endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
	                status, data_criacao, sub_total, taxa_frete, valor_total)
values (1, 1, 1, 1, 1, '38400-000', 'Rua Floriano Peixoto', '500', 'Apto 801', 'Brasil',
        'CRIADO', current_timestamp, 298.90, 10, 308.90);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (1, 1, 1, 1, 78.9, 78.9, null);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (2, 1, 2, 2, 110, 220, 'Menos picante, por favor');

insert into pedido (id, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, 
                    endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
	                status, data_criacao, sub_total, taxa_frete, valor_total)
values (2, 4, 1, 2, 1, '38400-111', 'Rua Acre', '300', 'Casa 2', 'Centro',
        'CRIADO', current_timestamp, 79, 0, 79);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (3, 2, 6, 1, 79, 79, 'Ao ponto');

insert into pedido (id, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, 
                    endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
	                status, data_criacao, data_confirmacao, data_entrega, sub_total, taxa_frete, valor_total)
values (3, 1, 1, 1, 1, '38400-222', 'Rua Natal', '200', null, 'Brasil',
        'ENTREGUE', '2019-10-30 21:10:00', '2019-10-30 21:10:45', '2019-10-30 21:55:44', 110, 10, 120);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (4, 3, 2, 1, 110, 110, null);

insert into pedido (id, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, 
                    endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
	                status, data_criacao, data_confirmacao, data_entrega, sub_total, taxa_frete, valor_total)
values (4, 1, 2, 1, 1, '38400-800', 'Rua Fortaleza', '900', 'Apto 504', 'Centro',
        'ENTREGUE', '2019-11-02 20:34:04', '2019-11-02 20:35:10', '2019-11-02 21:10:32', 174.4, 5, 179.4);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (5, 4, 3, 2, 87.2, 174.4, null);

insert into pedido (id, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, 
                    endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
	                status, data_criacao, data_confirmacao, data_entrega, sub_total, taxa_frete, valor_total)
values (5, 1, 3, 2, 1, '38400-200', 'Rua 10', '930', 'Casa 20', 'Martins',
        'ENTREGUE', '2019-11-02 21:00:30', '2019-11-02 21:01:21', '2019-11-02 21:20:10', 87.2, 10, 97.2);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (6, 5, 3, 1, 87.2, 87.2, null);