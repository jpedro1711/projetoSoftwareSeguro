create database projeto_softwareseguro;
use projeto_softwareseguro;

create table produto (
	id int primary key auto_increment,
    valorVenda decimal not null,
    custoUnitario decimal not null,
    quantidadeEstoque int,
    quantidadeMinimaEstoque int,
    categoriaId int
);

# exemplo de date: '2024-05-07'
create table venda (
	id int primary key auto_increment,
    dataVenda date 
);

create table venda_produto (
    id int primary key auto_increment,
    venda_id int,
    produto_id int,
    quantidade int,
    foreign key (venda_id) references venda(id),
    foreign key (produto_id) references produto(id)
);