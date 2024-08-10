-- Aqui podemos ter comandos DML (insert, update e delete)
-- podem haver mais de 1 instrução.
-- Cada instrução SEMPRE deve terminar com ; (ponto e vírgula)
insert into compositor (nome, apelido) values
('poeta 1', 'p1'),
('criativo 2', 'c2');

insert into musica
(nome, ouvintes_total, lancamento, estilo, classico, compositor_codigo)
values
('musica 1', 1, '2023-01-01', 'pop', true, 1),
('musica 2', 22, '2022-01-01', 'pop', false, 1),
('musica 3', 333, '2021-01-01', 'rock', true, 2),
('musica 4', 4444, '2020-01-01', 'rock', true, 2),
('melodia 5', 55555, '2019-01-01', 'funk', false, 2);