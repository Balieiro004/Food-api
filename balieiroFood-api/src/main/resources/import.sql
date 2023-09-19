insert into cozinha (nome) values ('Tailandesa');
insert into cozinha (nome) values ('Indiana');

insert into restaurante (nome, taxa_frete, cozinha_id) values ('Thai Gourmet', 10, 1);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Thai Delivery', 9.50, 1);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Tuk Tuk Comida Indiana', 15, 2);

insert into forma_pagamento(descricao) values ("Dinheiro");
insert into forma_pagamento(descricao) values ("Cartão de crédito");
insert into forma_pagamento(descricao) values ("Cartão de débito");
insert into forma_pagamento(descricao) values ("Pix");

insert into permissao(nome, descricao) values ("Anderson", "Autorizado retirada");
insert into permissao(nome, descricao) values ("Gustavo", "Não enviar");

insert into estado(nome) values ("São Paulo");
insert into estado(nome) values ("Rio de Janeiro");
insert into estado(nome) values ("Bahia");


insert into cidade(nome, estado_id) values ("Araraquara", 1);
insert into cidade(nome, estado_id) values ("São Bernardo", 2);
insert into cidade(nome, estado_id) values ("Pipas", 3);
insert into cidade(nome, estado_id) values ("São Carlos", 1);
insert into cidade(nome, estado_id) values ("Rio", 2);
