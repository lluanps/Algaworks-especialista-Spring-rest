CREATE TABLE foto_produto (
    produto_id INTEGER,
    nome_arquivo VARCHAR(150),
    descricao VARCHAR(150),
    content_type VARCHAR(80),
    tamanho INTEGER,
    PRIMARY KEY (produto_id),
    FOREIGN KEY (produto_id) REFERENCES produto(id)
);