USE SQLgraphs
SELECT * FROM PAIS
SELECT * FROM FABRICANTE
SELECT * FROM FORNECEDOR
select * from PECA
select * from RELFABFORN
select * from RELPAISFAB
select * from RELPAISFORN

insert into PAIS values(1)
insert into PAIS values(2)
insert into PAIS values(3)
insert into PAIS values(4)
insert into PAIS values(5)
insert into PAIS values(6)
insert into PAIS values(7)
insert into PAIS values(8)
insert into PAIS values(9)
insert into PAIS values(10)

insert into PECA values('1')
insert into PECA values('2')
insert into PECA values('3')
insert into PECA values('4')
insert into PECA values('5')
insert into PECA values('6')
insert into PECA values('7')
insert into PECA values('8')
insert into PECA values('9')
insert into PECA values('10')



insert into FABRICANTE values(1)
insert into FABRICANTE values(2)
insert into FABRICANTE values(3)
insert into FABRICANTE values(4)
insert into FABRICANTE values(5)
insert into FABRICANTE values(6)
insert into FABRICANTE values(7)
insert into FABRICANTE values(8)

insert into FORNECEDOR values(1)
insert into FORNECEDOR values(2)
insert into FORNECEDOR values(3)
insert into FORNECEDOR values(4)
insert into FORNECEDOR values(5)
insert into FORNECEDOR values(6)
insert into FORNECEDOR values(7)
insert into FORNECEDOR values(8)

insert into RELFABFORN values(1,2)
insert into RELFABFORN values(1,1)
insert into RELFABFORN values(2,6)
insert into RELFABFORN values(3,5)
insert into RELFABFORN values(4,3)
insert into RELFABFORN values(2,8)
insert into RELFABFORN values(5,1)
insert into RELFABFORN values(8,1)
insert into RELFABFORN values(7,4)

insert into RELPAISFAB values(1,1)
insert into RELPAISFAB values(5,2)
insert into RELPAISFAB values(7,3)
insert into RELPAISFAB values(6,4)
insert into RELPAISFAB values(7,5)
insert into RELPAISFAB values(10,6)
insert into RELPAISFAB values(9,7)
insert into RELPAISFAB values(7,8)

insert into RELPAISFORN values(3,1)
insert into RELPAISFORN values(7,2)
insert into RELPAISFORN values(8,3)
insert into RELPAISFORN values(9,4)
insert into RELPAISFORN values(3,5)
insert into RELPAISFORN values(5,6)
insert into RELPAISFORN values(7,7)
insert into RELPAISFORN values(7,8)
insert into RELPAISFORN values(8,9)

