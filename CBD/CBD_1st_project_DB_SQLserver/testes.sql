INSERT INTO PESSOA VALUES('asdf')
USE SQLrelacional
select * from PESSOA

USE SQLdoc
select * from DOC_PESSOA
DELETE FROM DOC_PESSOA
WHERE n_bi='151516';


DELETE FROM PESSOA
WHERE n_bi='212121';

USE SQLgraphs
select * from PESSOA
select * from FAMILIA
SELECT * FROM PECA
delete from PESSOA
where id_pessoa='1' or  id_pessoa='3' or  id_pessoa='4' or  id_pessoa='6'

USE SQLdoc
select * from DOC_CARRO
select * from DOC_PESSOA

USE SQLrelacional
SELECT * FROM PAIS
select * from MARCA
select * from PESSOA
SELECT * FROM PECAS
SELECT * FROM MODELO
delete from PESSOA where id_pessoa='3'
USE SQLrelacional
SELECT * FROM SUBMODELO
SELECT * FROM MODELO
select * from MARCA

USE SQLrelacional
select * from MARCA
use SQLgraphs
select * from RELPAISMARCA
SELECT * FROM RELFABMARCA
SELECT * FROM RELPAISFAB
select * from RELSUBMODELOPECA
SELECT * FROM RELMODELO_SUBMODELO
SELECT * FROM RELMODELOMARCA
SELECT * from MARCA
SELECT * from MODELO
SELECT * from SUB_MODELO
--RELAÇÕES:
SELECT * FROM RELFABFORN
SELECT * FROM RELPAISMARCA
SELECT * FROM RELPAISFAB
SELECT * FROM RELPAISFORN
SELECT * FROM RELPAISPESSOA

SELECT * FROM RELMODELOMARCA
SELECT * FROM RELMODELO_SUBMODELO
SELECT * FROM RELPESSOASUBMODELO


use SQLgraphs
delete from MODELO
where id_modelo='2'
delete from SUB_MODELO
where id_sub_modelo='4'

USE SQLrelacional

SELECT * from MARCA
SELECT * from MODELO
SELECT * from SUBMODELO
delete from SUBMODELO WHERE id_subModelo='4'



USE SQLkey_value
select * from PAIS_IMG
SELECT * FROM CARRO_IMG