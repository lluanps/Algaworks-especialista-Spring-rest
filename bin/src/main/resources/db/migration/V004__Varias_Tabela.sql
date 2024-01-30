CREATE TABLE forma_pagamento (
    id serial PRIMARY KEY,
    descricao varchar(60) NOT NULL
);

CREATE TABLE grupo (
    id serial PRIMARY KEY,
    nome varchar(60) NOT NULL
);

CREATE TABLE permissao (
    id serial PRIMARY KEY,
    descricao varchar(60) NOT NULL,
    nome varchar(100) NOT NULL
);

CREATE TABLE restaurante (
    id serial PRIMARY KEY,
    cozinha_id integer NOT NULL,
    nome varchar(80) NOT NULL,
    taxa_frete numeric(10,2) NOT NULL,
    data_atualizacao timestamp NOT NULL,
    data_cadastro timestamp NOT NULL,
    endereco_cidade_id integer,
    endereco_cep varchar(9),
    endereco_logradouro varchar(100),
    endereco_numero varchar(20),
    endereco_complemento varchar(60),
    endereco_bairro varchar(60),
    FOREIGN KEY (cozinha_id) REFERENCES cozinha (id),
    FOREIGN KEY (endereco_cidade_id) REFERENCES cidade (id)
);

CREATE TABLE grupo_permissao (
    grupo_id integer NOT NULL,
    permissao_id integer NOT NULL,
    PRIMARY KEY (grupo_id, permissao_id),
    FOREIGN KEY (permissao_id) REFERENCES permissao (id),
    FOREIGN KEY (grupo_id) REFERENCES grupo (id)
);

CREATE TABLE produto (
    id serial PRIMARY KEY,
    restaurante_id integer NOT NULL,
    nome varchar(80) NOT NULL,
    descricao text NOT NULL,
    preco numeric(10,2) NOT NULL,
    ativo boolean NOT NULL,
    FOREIGN KEY (restaurante_id) REFERENCES restaurante (id)
);

CREATE TABLE restaurante_forma_pagamento (
    restaurante_id integer NOT NULL,
    forma_pagamento_id integer NOT NULL,
    PRIMARY KEY (restaurante_id, forma_pagamento_id),
    FOREIGN KEY (forma_pagamento_id) REFERENCES forma_pagamento (id),
    FOREIGN KEY (restaurante_id) REFERENCES restaurante (id)
);

CREATE TABLE usuario (
    id bigserial PRIMARY KEY,
    nome varchar(80) NOT NULL,
    email varchar(255) NOT NULL,
    senha varchar(255) NOT NULL,
    data_cadastro timestamp NOT NULL
);

CREATE TABLE usuario_grupo (
    usuario_id bigint NOT NULL,
    grupo_id bigint NOT NULL,
    PRIMARY KEY (usuario_id, grupo_id),
    FOREIGN KEY (usuario_id) REFERENCES usuario (id)
);
