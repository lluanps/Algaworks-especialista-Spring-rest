ALTER TABLE pedido ADD COLUMN endereco_cep varchar(11);

ALTER TABLE pedido ADD column forma_pagamento_id INTEGER;
ALTER TABLE pedido ADD CONSTRAINT fk_pedido_forma_pagamento
FOREIGN KEY (forma_pagamento_id) REFERENCES forma_pagamento (id);