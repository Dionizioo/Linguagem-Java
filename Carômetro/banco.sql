create database dbcarametro;
use dbcarametro;
create table alunos(
	ra int primary key auto_increment,
    nome varchar(30) not null,
    foto longblob not null
);

describe alunos;

select * from alunos;

select * from alunos where ra = 1;

select * from alunos where nome like 'c%'  order by nome;
