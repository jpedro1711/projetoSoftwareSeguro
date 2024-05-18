drop database projeto_softwareseguro;
create database projeto_softwareseguro;
use projeto_softwareseguro;

create table produto (
	id int primary key auto_increment,
    nomeProduto varchar(150),
    valorVenda decimal not null,
    custoUnitario decimal not null,
    quantidadeEstoque int,
    quantidadeMinimaEstoque int
);

INSERT INTO produto (nomeProduto, valorVenda, custoUnitario, quantidadeEstoque, quantidadeMinimaEstoque)
VALUES ('Produto Exemplo', 100.00, 50.00, 200, 50);

select * from produto;
# exemplo de date: '2024-05-07'
create table venda (
	id int primary key auto_increment,
    dataVenda date ,
    valorTotal decimal
);
select * from venda;
create table items_venda (
    id int primary key auto_increment,
    venda_id int,
    produto_id int,
    quantidade int,
    foreign key (venda_id) references venda(id),
    foreign key (produto_id) references produto(id)
);
select * from items_venda;