create table pessoas (
id varchar(255) not null primary key,
nome varchar(255) not null,
email varchar(255) not null,
senha varchar(255),
cpf char(11) unique not null,
idade integer not null,
cep varchar(9),
endereco varchar(255),
numero_casa varchar(10),
cargo varchar(55),
salario decimal,
bonus_cargo decimal
);

create table produtos(
id varchar(255) not null primary key,
nome varchar(255) unique not null,
quantidade integer not null,
valor decimal not null, 
categoria varchar(55)
);

create table pedidos(
id varchar(255) not null primary key,
pessoa_id varchar(255) not null references pessoas(id),
descricao varchar(255),
valor_total decimal not null
);

create table pedido_produtos (
id varchar(255) not null primary key,
pedido_id varchar(255) not null references pedidos(id),
produto_id varchar(255) not null references produtos(id),
quantidade_produto integer not null,
valor_produto decimal not null
);