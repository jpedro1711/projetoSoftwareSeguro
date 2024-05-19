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

create table venda (
	id int primary key auto_increment,
    dataVenda date ,
    valorTotal decimal
);

create table items_venda (
    id int primary key auto_increment,
    venda_id int,
    produto_id int,
    quantidade int,
    foreign key (venda_id) references venda(id),
    foreign key (produto_id) references produto(id)
);

INSERT INTO produto (nomeProduto, valorVenda, custoUnitario, quantidadeEstoque, quantidadeMinimaEstoque)
VALUES 
('Produto Exemplo', 100.00, 50.00, 200, 50),
('Produto Exemplo2', 100.00, 50.00, 40, 50),
('Produto Exemplo3', 150.00, 70.00, 100, 30),
('Produto Exemplo4', 200.00, 100.00, 300, 80),
('Produto Exemplo5', 250.00, 120.00, 50, 20);

INSERT INTO venda (dataVenda, valorTotal)
VALUES 
('2024-05-07', 500.00),
('2024-05-08', 700.00),
('2024-05-09', 200.00),
('2024-05-10', 300.00);


INSERT INTO items_venda (venda_id, produto_id, quantidade)
VALUES 
(1, 1, 5),
(1, 2, 2),
(2, 1, 3),
(2, 3, 7),
(3, 4, 1),
(3, 5, 4),
(4, 1, 6),
(4, 2, 5),
(4, 3, 2);

select * from items_venda;

select * from produto
where quantidadeEstoque < quantidadeMinimaEstoque;

select sum(valorTotal) from venda;

select produto.NomeProduto, count(*) as contagem from produto
inner join items_venda as a
on a.produto_id = produto.id
inner join venda as b
on b.id = a.venda_id
group by produto_id
order by contagem DESC
limit 3;