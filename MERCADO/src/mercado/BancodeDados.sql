create database bagualshop;
use bagualshop;

create table usuario(
	nome varchar(200),
	senha varchar(100) not null,
	tipo varchar(7) not null,
	usuario_id int primary key auto_increment
);

create table item(
    preco float,
    nome varchar(100),
    item_id int primary key auto_increment,
    tipo varchar(300),
    descricao varchar(1000),
    imagem_prod blob
);

create table carrinho(
	item_id int,
	usuario_id int,
    primary key (item_id, usuario_id),
    foreign key (item_id) references item(item_id),
    foreign key (usuario_id) references usuario(usuario_id)
);

insert into carrinho values
	(1, 1),
    (2, 1),
    (1, 3),
    (2, 2),
    (3, 3);

select * from usuario;
select * from item;
SELECT usuario.nome, item.nome FROM carrinho, usuario, item where carrinho.usuario_id = usuario.usuario_id and carrinho.item_id = item.item_id;