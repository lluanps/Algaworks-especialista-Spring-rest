CREATE TABLE pedido (
    id SERIAL PRIMARY KEY,
    sub_total DECIMAL(10,2) NOT NULL,
    taxa_frete DECIMAL(10,2) NOT NULL,
    valor_total DECIMAL(10,2) NOT NULL,
    data_criacao TIMESTAMP NOT NULL,
    data_confirmacao TIMESTAMP,
    data_cancelamento TIMESTAMP,
    data_entrega TIMESTAMP,
    restaurante_id BIGINT NOT NULL,
    usuario_cliente_id BIGINT NOT NULL,
    endereco_cidade_id BIGINT NOT NULL,
    endereco_logradouro VARCHAR(100) NOT NULL,
    endereco_numero VARCHAR(20) NOT NULL,
    endereco_complemento VARCHAR(60),
    endereco_bairro VARCHAR(60) NOT NULL,
    status_pedido VARCHAR(255),

    CONSTRAINT fk_pedido_restaurante FOREIGN KEY (restaurante_id) REFERENCES restaurante (id),
    CONSTRAINT fk_pedido_usuario_cliente FOREIGN KEY (usuario_cliente_id) REFERENCES usuario (id)
);

CREATE TABLE item_pedido (
    id SERIAL PRIMARY KEY,
    quantidade INTEGER,
    preco_unitario DECIMAL(10,2),
    preco_total DECIMAL(10,2),
    observacao VARCHAR(255),
    pedido_id INTEGER,
    produto_id INTEGER,

    CONSTRAINT fk_item_pedido_pedido FOREIGN KEY (pedido_id) REFERENCES pedido(id),
    CONSTRAINT fk_item_pedido_produto FOREIGN KEY (produto_id) REFERENCES produto(id)
)

