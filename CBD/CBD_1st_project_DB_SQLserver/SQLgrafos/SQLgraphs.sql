USE SQLgraphs
CREATE TABLE PESSOA(
id_pessoa		INT CONSTRAINT PKIDPESSOA PRIMARY KEY,
);

CREATE TABLE PAIS(
id_pais			INT PRIMARY KEY,
);

CREATE TABLE FABRICANTE(
id_fabricante	INT PRIMARY KEY,
);

CREATE TABLE FORNECEDOR(
id_fornecedor	INT PRIMARY KEY,
);

CREATE TABLE MARCA(
id_marca		INT PRIMARY KEY,
);

CREATE TABLE MODELO(
id_modelo		INT PRIMARY KEY,
);

CREATE TABLE SUB_MODELO(
id_sub_modelo	INT PRIMARY KEY,
);

CREATE TABLE PECA (
id_peca			INT CONSTRAINT PKIDPECA PRIMARY KEY
);

CREATE TABLE FAMILIA(
id_p1			INT,
id_p2			INT,
FOREIGN KEY(id_p1) REFERENCES PESSOA(id_pessoa),
FOREIGN KEY(id_p2) REFERENCES PESSOA(id_pessoa)
);

CREATE TABLE RELFABMARCA(
id_marca		INT,
id_fabricante	INT,
FOREIGN KEY(id_marca) REFERENCES MARCA(id_marca),
FOREIGN KEY(id_fabricante) REFERENCES FABRICANTE(id_fabricante)
);

CREATE TABLE RELMODELOMARCA(
id_modelo		INT,
id_marca		INT,
FOREIGN KEY(id_modelo) REFERENCES MODELO(id_modelo),
FOREIGN KEY(id_marca) REFERENCES MARCA(id_marca),
);
CREATE TABLE RELMODELO_SUBMODELO(
id_sub_modelo	INT,
id_modelo		INT,
FOREIGN KEY(id_sub_modelo) REFERENCES SUB_MODELO(id_sub_modelo),
FOREIGN KEY(id_modelo) REFERENCES MODELO(id_modelo),
)

CREATE TABLE RELPESSOASUBMODELO(
id_pessoa		INT,
id_sub_modelo	INT,
FOREIGN KEY (id_pessoa) REFERENCES PESSOA(id_pessoa),
FOREIGN KEY (id_sub_modelo) REFERENCES SUB_MODELO(id_sub_modelo),
);

CREATE TABLE RELSUBMODELOPECA(
id_sub_modelo	INT,
id_peca			INT,
FOREIGN KEY (id_sub_modelo) REFERENCES SUB_MODELO(id_sub_modelo),
FOREIGN KEY (id_peca) REFERENCES PECA(id_peca),
);

CREATE TABLE RELFABFORN(
id_fornecedor	INT,
id_fabricante	INT,
FOREIGN KEY(id_fornecedor) REFERENCES FORNECEDOR(id_fornecedor),
FOREIGN KEY(id_fabricante) REFERENCES FABRICANTE(id_fabricante),
);

CREATE TABLE RELPAISMARCA(
id_pais		INT,
id_marca	INT,
FOREIGN KEY(id_pais) REFERENCES PAIS(id_pais),
FOREIGN KEY(id_marca) REFERENCES MARCA(id_marca),
);

CREATE TABLE RELPAISFAB(
id_pais			INT,
id_fabricante	INT,
FOREIGN KEY(id_pais) REFERENCES PAIS(id_pais),
FOREIGN KEY(id_fabricante) REFERENCES FABRICANTE(id_fabricante)
);

CREATE TABLE RELPAISFORN(
id_pais			INT,
id_fornecedor	INT,
FOREIGN KEY(id_pais) REFERENCES PAIS(id_pais),
FOREIGN KEY(id_fornecedor) REFERENCES FORNECEDOR(id_fornecedor) 
);

CREATE TABLE RELPAISPESSOA(
id_pais		   INT,
id_pessoa	   INT,
FOREIGN KEY(id_pais) REFERENCES PAIS(id_pais),
FOREIGN KEY(id_pessoa) REFERENCES PESSOA(id_pessoa)
);