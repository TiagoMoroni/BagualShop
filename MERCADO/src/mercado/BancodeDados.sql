create database bagualshop;
use bagualshop;

create table usuario(
	nome varchar(200),
	senha varchar(100),
	tipo varchar(7),
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
