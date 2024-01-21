CREATE TABLE restaurante_usuario_responsavel (
	restaurante_id integer NOT NULL,
	usuario_id integer NOT NULL,
	foreign key (restaurante_id) references restaurante(id),
	foreign key (usuario_id) references usuario(id)
);