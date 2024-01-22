DROP TYPE StatusPedido;
CREATE TYPE status AS ENUM ('CRIADO', 'CONFIRMADO', 'ENTREGUE', 'CANCELADO');

DROP TABLE item_pedido;
DROP TABLE pedido;

CREATE TABLE pedido (
	id SERIAL PRIMARY KEY,
	restaurante_id INTEGER,
	usuario_cliente_id INTEGER,
	forma_pagamento_id INTEGER,
	endereco_cidade_id INTEGER,
	endereco_cep VARCHAR(11),
	endereco_logradouro VARCHAR(80),
	endereco_numero VARCHAR(20),
	endereco_complemento VARCHAR(60),
	endereco_bairro VARCHAR(80),
    status VARCHAR(255),
	data_criacao TIMESTAMP,
	data_confirmacao TIMESTAMP,
	data_entrega TIMESTAMP,
	subtotal DECIMAL(10,2),
	taxa_frete DECIMAL(10,2),
	valor_total DECIMAL(10,2),
	FOREIGN KEY (restaurante_id) REFERENCES restaurante (id),
	FOREIGN KEY (usuario_cliente_id) REFERENCES usuario (id),
	FOREIGN KEY (forma_pagamento_id) REFERENCES forma_pagamento (id),
	FOREIGN KEY (restaurante_id) REFERENCES restaurante (id),
	FOREIGN KEY (endereco_cidade_id) REFERENCES cozinha (id)
);

CREATE TABLE item_pedido (
	id SERIAL PRIMARY KEY,
	pedido_id INTEGER,
	produto_id INTEGER,
	quantidade INTEGER,
	preco_unitario DECIMAL(10,2),
	preco_total DECIMAL(10,2),
	observacao VARCHAR(255),
	FOREIGN KEY (pedido_id) REFERENCES pedido (id),
	FOREIGN KEY (produto_id) REFERENCES produto (id)
);