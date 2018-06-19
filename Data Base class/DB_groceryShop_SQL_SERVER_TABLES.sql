CREATE DATABASE db_project_grocery;
GO

USE db_project_grocery;
GO

CREATE SCHEMA grocery;
GO

-----------------------------------------------------------
--------------------------TABLES---------------------------
-----------------------------------------------------------

CREATE TABLE grocery.CLIENTE(
	Nif				INT PRIMARY KEY,
	Nome			VARCHAR(50) NOT NULL,
	Morada			VARCHAR(50) NOT NULL,
	Contacto		VARCHAR(50) NOT NULL,
);
GO

CREATE TABLE grocery.FUNCIONARIO(
	Nif				INT PRIMARY KEY,
	Nome			VARCHAR(50) NOT NULL,
	Morada			VARCHAR(50) NOT NULL,
	Contacto		VARCHAR(50) NOT NULL,
	CC				INT NOT NULL,
	Salario			DECIMAL(10,2) NOT NULL,
);
GO

CREATE TABLE grocery.VENDA(
	ID				INT IDENTITY(1,1) PRIMARY KEY,
	Valor			DECIMAL(10,2),
	Nif_func		INT NOT NULL,
	Nif_cliente		INT NOT NULL,
	DataVenda		DATE NOT NULL DEFAULT GETDATE(),
	FOREIGN KEY (Nif_func) REFERENCES grocery.FUNCIONARIO(Nif),
	FOREIGN KEY (Nif_cliente) REFERENCES grocery.CLIENTE(Nif)
);
GO

CREATE TABLE grocery.CONTA_CORRENTE(
	ID				INT IDENTITY(1,1) PRIMARY KEY,
	IN_cash			DECIMAL(10,2),
	OUT_cash		DECIMAL(10,2),
	Nif_cliente		INT NOT NULL,
	Saldo			AS (IN_cash - OUT_cash),
	FOREIGN KEY (Nif_cliente) REFERENCES grocery.CLIENTE(Nif)
);
GO

CREATE TABLE grocery.TIPO_PAGAMENTO(
	ID				INT IDENTITY(1,1) PRIMARY KEY,
	Tipo			VARCHAR(30) NOT NULL,
);
GO

CREATE TABLE grocery.PAGAMENTO(
	DataPagm		DATE NOT NULL DEFAULT GETDATE(),
	ID_venda		INT NOT NULL,
	Valor			DECIMAL(10,2) NOT NULL,
	ID_conta_corr	INT,
	ID_tipo_pag		INT,
	PRIMARY KEY(DataPagm, ID_venda),
	FOREIGN KEY (ID_venda) REFERENCES grocery.VENDA(ID),
	FOREIGN KEY (ID_conta_corr) REFERENCES grocery.CONTA_CORRENTE(ID),
	FOREIGN KEY (ID_tipo_pag) REFERENCES grocery.TIPO_PAGAMENTO(ID)
);
GO

CREATE TABLE grocery.IVA(
	Codigo			INT IDENTITY(1,1) PRIMARY KEY,
	Nome			VARCHAR(30) NOT NULL,
	Percentagem		INT NOT NULL,
	Estado			BIT NOT NULL
);
GO

CREATE TABLE grocery.PRODUTO(
	ID				INT IDENTITY(1,1) PRIMARY KEY,
	Nome			VARCHAR(50) NOT NULL,
	Percent_lucro	INT NOT NULL,
	PVP				DECIMAL(10,2) NOT NULL,
	Stock_min		INT,
	Stock_atual		INT,
	Codigo_IVA		INT NOT NULL,
	FOREIGN KEY (Codigo_IVA) REFERENCES grocery.IVA(Codigo)
);
GO

CREATE TABLE grocery.ITEM_VENDA(
	ID_venda		INT NOT NULL,
	ID_produto		INT NOT NULL,
	Quantidade		INT NOT NULL,
	Desconto		FLOAT,
	FOREIGN KEY (ID_venda) REFERENCES grocery.VENDA(ID),
	FOREIGN KEY (ID_produto) REFERENCES grocery.PRODUTO(ID)
);
GO

CREATE TABLE grocery.FORNECEDOR(
	Nif				INT PRIMARY KEY,
	Contacto		VARCHAR(50) NOT NULL,
	Nome			VARCHAR(50) NOT NULL
);
GO


CREATE TABLE grocery.ENCOMENDAS(
	ID				INT IDENTITY(1,1) PRIMARY KEY,
	NumFatura		VARCHAR(50),
	ID_forn			INT NOT NULL,
	ID_func			INT NOT NULL,
	DataEncomenda	DATE NOT NULL DEFAULT GETDATE(),
	FOREIGN KEY (ID_forn) REFERENCES grocery.FORNECEDOR(Nif),
	FOREIGN KEY (ID_func) REFERENCES grocery.FUNCIONARIO(Nif)
);
GO

CREATE TABLE grocery.ITEM_ENCOMENDA(
	ID_encomenda	INT NOT NULL,
	ID_produto		INT NOT NULL,
	Quantidade		INT NOT NULL,
	Preco_forn		DECIMAL(10,2),
	FOREIGN KEY (ID_encomenda) REFERENCES grocery.ENCOMENDAS(ID),
	FOREIGN KEY (ID_produto) REFERENCES grocery.PRODUTO(ID)
);
GO

CREATE TABLE grocery.ITEM_FORNECEDOR(
	ID_fornecedor	INT NOT NULL,
	ID_produto		INT NOT NULL,
	Preco_forn		DECIMAL(10,2) NOT NULL,
	Data_rec		DATE NOT NULL DEFAULT GETDATE(),
	FOREIGN KEY (ID_fornecedor) REFERENCES grocery.FORNECEDOR(Nif),
	FOREIGN KEY (ID_produto) REFERENCES grocery.PRODUTO(ID)
);
GO

---------------------------------------------------
-----------------------VIEWS-----------------------
---------------------------------------------------

--View select clientes
CREATE VIEW grocery.VIEW_CLIENTES
AS
	SELECT CLIENTE.Nome AS Nome, CLIENTE.Nif AS NIF, CLIENTE.Morada AS Morada, CLIENTE.Contacto AS Contacto
	FROM grocery.CLIENTE
GO

--View para devolver os produtos com todas as informações e o valor do iva
CREATE VIEW grocery.VIEW_PRODUTO_IVA
AS
	SELECT  prod.ID AS ID, prod.Nome AS Artigo, prod.Stock_min AS 'Stock min', prod.Stock_atual AS 'Stock Atual', CONVERT(NVARCHAR(5),iva.Percentagem)+' %' AS IVA, CONVERT(NVARCHAR(30),prod.PVP) AS 'PvP €'
	FROM grocery.PRODUTO AS prod JOIN grocery.IVA AS iva ON prod.Codigo_IVA=iva.Codigo
GO

--View para devolver apenas informações necessarias dos produtos para as vendas
CREATE VIEW grocery.VIEW_PRODUTO_NAVENDA
AS
	SELECT  prod.ID AS ID, prod.Nome AS Artigo, prod.Stock_atual AS 'Stock Atual', CONVERT(NVARCHAR(5),iva.Percentagem)+' %' AS IVA, CONVERT(NVARCHAR(30),prod.PVP) AS 'PvP €'
	FROM grocery.PRODUTO AS prod JOIN grocery.IVA AS iva ON prod.Codigo_IVA=iva.Codigo
GO

--View para IVAS activos
CREATE VIEW grocery.VIEW_IVAS_ACTIVOS
AS
	SELECT CONVERT(NVARCHAR(10),iva.Codigo)+'- '+CONVERT(NVARCHAR(30),iva.Nome)+' '+CONVERT(NVARCHAR(10),iva.Percentagem)+'%' AS IVA
	FROM grocery.IVA as iva
	WHERE iva.Estado=1
GO

--View para obter funcionarios com nome e nif
CREATE VIEW grocery.VIEW_FUNCIONARIOS_NIFnome
AS
	SELECT CONVERT(NVARCHAR(10),func.Nif)+'- '+CONVERT(NVARCHAR(50),func.Nome) AS Funcionario
	FROM grocery.FUNCIONARIO as func
GO

--View para obter tipos de pagamento
CREATE VIEW grocery.VIEW_TIPO_PAGAMENTO
AS
	SELECT CONVERT(nvarchar(5),tipoPagm.ID)+'- '+CONVERT(NVARCHAR(20),tipoPagm.Tipo) AS TipoPagam
	FROM grocery.TIPO_PAGAMENTO AS tipoPagm
GO

--View select funcionarios
CREATE VIEW grocery.VIEW_FUNCIONARIOS
AS
	Select FUNCIONARIO.Nome AS Nome, FUNCIONARIO.Nif AS NIF, FUNCIONARIO.Morada AS Morada, FUNCIONARIO.Contacto AS Contacto, FUNCIONARIO.CC AS CC, FUNCIONARIO.Salario AS Salario 
	FROM grocery.FUNCIONARIO
GO

--View select fornecedores
CREATE VIEW grocery.VIEW_FORNECEDORES
AS
	Select FORNECEDOR.Nif AS NIF, FORNECEDOR.Contacto AS Contacto, FORNECEDOR.Nome AS Nome
	FROM grocery.FORNECEDOR
GO

--View select ivas
CREATE VIEW grocery.VIEW_IVAS
AS
	Select IVA.Codigo AS Codigo, IVA.Nome AS Nome, IVA.Percentagem AS Percentagem, IVA.Estado AS Estado
	FROM grocery.IVA
GO

-- View para ultimo id da encomenda
CREATE VIEW grocery.VIEW_ENCOMENDA_LAST_ID
AS
	SELECT IDENT_CURRENT('grocery.ENCOMENDAS') AS lastID
GO

--view para devolver informaçoes de produtos com stock minimo atingido
CREATE VIEW grocery.VIEW_PRODUTO_STOCKMINIMO
AS
	SELECT  prod.ID AS ID, prod.Nome AS Artigo, prod.Stock_atual AS 'Stock Atual', CONVERT(NVARCHAR(5),iva.Percentagem)+' %' AS IVA, CONVERT(NVARCHAR(30),prod.PVP) AS 'PvP €'
	FROM grocery.PRODUTO AS prod JOIN grocery.IVA AS iva ON prod.Codigo_IVA=iva.Codigo
	WHERE prod.Stock_atual < prod.Stock_min
GO

--View para preencher combobox de receber encomendas
CREATE VIEW grocery.VIEW_ID_ENCOMENDA_FORNECEDOR
AS
	SELECT CONVERT(NVARCHAR(10),enc.ID)+'- '+CONVERT(NVARCHAR(30),enc.DataEncomenda)+'- '+CONVERT(NVARCHAR(50),forn.Nome) AS Encomenda_fornecedor
	FROM grocery.ENCOMENDAS AS enc JOIN grocery.FORNECEDOR AS forn ON enc.ID_forn=forn.Nif
	WHERE enc.NumFatura is null
GO

--Obter ID da ultima encomenda criada(Funciona porque nao há nada concorrencial na tabela)
CREATE VIEW grocery.VIEW_VENDA_LAST_ID
AS
	SELECT IDENT_CURRENT('grocery.VENDA') AS lastID
GO


------------------------------------------------------------
-------------------------PROCEDURES-------------------------
------------------------------------------------------------

--Criar cliente com conta corrente
CREATE PROC grocery.sp_CriarCliente 
	@nif INT,
	@nome VARCHAR(50),
	@morada VARCHAR(50),
	@contacto VARCHAR(50)
AS
BEGIN
	INSERT INTO grocery.CLIENTE 
	VALUES (@nif,@nome,@morada,@contacto)
END
BEGIN
	INSERT INTO grocery.CONTA_CORRENTE 
	(IN_cash,OUT_cash,Nif_cliente)
	VALUES (0.0,0.0,@nif)
END
GO

--Update cliente SP
CREATE PROC grocery.sp_EditarCliente 
	@nome VARCHAR(50),
	@nif INT,
	@morada VARCHAR(50),
	@contacto VARCHAR(50)
AS
BEGIN
	UPDATE grocery.CLIENTE SET Nome = @nome, Morada = @morada, Contacto = @contacto 
	WHERE Nif = @nif
END
GO

--Criar funcionario
CREATE PROC grocery.sp_CriarFuncionario 
	@nif INT,
	@nome VARCHAR(50),
	@morada VARCHAR(50),
	@contacto VARCHAR(50),
	@cc INT,
	@salario DECIMAL(10, 2)
AS
BEGIN
	INSERT INTO grocery.FUNCIONARIO 
	VALUES (@nif,@nome,@morada,@contacto,@cc,@salario)
END
GO

--Criar fornecedor
CREATE PROCEDURE grocery.sp_AdicionarFornecedores
	@Nif int,
	@Contacto varchar(50),
	@Nome varchar(50)
AS
BEGIN
	INSERT INTO grocery.FORNECEDOR 
	VALUES (@nif,@contacto,@nome)
END
GO

--Criar produtos
CREATE PROC grocery.sp_CriarProduto
	@nome VARCHAR(50),
	@percLucro INT,
	@pvp DECIMAL(10,2),
	@stockMin INT,
	@stockAtual INT,
	@codIva INT
AS
BEGIN
	INSERT INTO grocery.PRODUTO 
	VALUES (@nome,@percLucro,@pvp,@stockMin,@stockAtual,@codIva)
END
GO

--Editar produto (incluindo IVA)
CREATE PROC grocery.sp_EditarProdutoComIVA
	@nome VARCHAR(50),
	@percLucro INT,
	@pvp DECIMAL(10,2),
	@stockMin INT,
	@stockAtual INT,
	@codIva INT,
	@id INT
AS
BEGIN
	UPDATE grocery.PRODUTO SET Nome = @nome, Percent_lucro = @percLucro, PVP = @pvp, Stock_min=@stockMin, Stock_atual=@stockAtual, Codigo_IVA=@codIva
	WHERE ID = @id
END
GO

--Editar produto (sem IVA)
CREATE PROC grocery.sp_EditarProdutoSemIVA
	@nome VARCHAR(50),
	@percLucro INT,
	@pvp DECIMAL(10,2),
	@stockMin INT,
	@stockAtual INT,
	@id INT
AS
BEGIN
	UPDATE grocery.PRODUTO SET Nome = @nome, Percent_lucro = @percLucro, PVP = @pvp, Stock_min=@stockMin, Stock_atual=@stockAtual
	WHERE ID = @id
END
GO

--criar IVA
CREATE PROCEDURE grocery.sp_AreaIvas(
	@Nome varchar(30),
	@Percentagem int,
	@Estado bit
	)
AS
BEGIN
	INSERT INTO grocery.IVA 
	VALUES(@Nome,@Percentagem,@Estado)
END
GO

--Update Fornecedor SP
CREATE PROC grocery.sp_EditarFornecedor
	@nome VARCHAR(50),
	@contacto VARCHAR(50),
	@nif int
AS 
BEGIN
	UPDATE grocery.FORNECEDOR SET Nome = @nome, Contacto = @contacto 
	WHERE Nif = @nif
END
GO

--Update Funcionario SP
CREATE PROC grocery.sp_EditarFuncionario
	@nif INT,
	@nome VARCHAR(50),
	@morada VARCHAR(50),
	@contacto VARCHAR(50),
	@salario DECIMAL(10,2)
AS
BEGIN
	UPDATE grocery.FUNCIONARIO SET Nome = @nome, Morada = @morada, Contacto = @contacto, Salario = @salario 
	WHERE Nif = @nif
END
GO

-- Adicionar numero de fatura
CREATE PROC grocery.sp_AdicionarNumeroFatura
	@idEnc int,
	@numFat VARCHAR(50)
AS 
	UPDATE grocery.ENCOMENDAS 
	SET NumFatura = @numFat 
	WHERE ID = @idEnc
GO

--Adicionar produtos a encomenda
CREATE PROC grocery.sp_AdicionarProdutosEncomenda
	@idEncomenda INT,
	@idProduto INT,
	@quantidade INT
AS
	INSERT INTO grocery.ITEM_ENCOMENDA 
	(ID_encomenda,ID_produto,Quantidade)
	VALUES(@idEncomenda,@idProduto,@quantidade);
GO

--Criar Encomenda
CREATE PROC grocery.sp_CriarEncomenda
	@idForn int,
	@idFunc int
AS
	INSERT INTO grocery.ENCOMENDAS (ID_forn,ID_func) VALUES (@idForn,@idFunc);
GO

--Criar nova venda
CREATE PROC grocery.sp_CRIARNOVAVENDA
	@nifCliente INT,
	@nifFunc INT
AS
	INSERT INTO grocery.VENDA
	(Nif_func,Nif_cliente)
	VALUES (@nifFunc,@nifCliente);
GO

--Adicionar produtos à tabela ITEMVENDA com o id da venda 
CREATE PROC grocery.sp_AdicionarArtigoITEMvenda
	@idVenda INT,
	@idProduto INT,
	@quantidade INT,
	@desconto INT
AS
BEGIN
	INSERT INTO grocery.ITEM_VENDA
	VALUES (@idVenda,@idProduto,@quantidade,@desconto);
END
BEGIN
	UPDATE grocery.PRODUTO SET Stock_atual = Stock_atual - @quantidade
	WHERE ID=@idProduto
END
GO

--atualiza o stock dos produtos apos receber encomenda
CREATE PROC grocery.sp_UpdateStockEncomendas
	@idProduto INT,
	@quantidade INT
AS
BEGIN
	UPDATE grocery.PRODUTO SET Stock_atual = Stock_atual + @quantidade
	WHERE ID=@idProduto
END
GO

--soma o valor da venda na divida do cliente(conta corrente)
CREATE PROC grocery.sp_InsertOUTContaCorrente
	@idContaCorr INT,
	@valor DECIMAL(10,2)
AS
	UPDATE grocery.CONTA_CORRENTE
	SET OUT_cash = OUT_cash + @valor
	WHERE ID=@idContaCorr
GO

--multiplica o preço de cada produto e soma os resultado para obter o total da venda
--faz update ao total da venda 
--verifica qual é o tipo de pagamento e cria-o
--caso seja para a conta corrente cria o pagamento e executa o SP sp_InsertOUTContaCorrente
CREATE PROC grocery.sp_PagamentoTotalValorVenda
	@idVenda INT,
	@tipoPagamento INT
AS
BEGIN
	DECLARE @total DECIMAL(10,2)
	DECLARE @idContaCorr INT
	DECLARE @idCliente INT

	SELECT @total=SUM(PROD.PVP * IV.Quantidade)
	FROM grocery.ITEM_VENDA AS IV JOIN grocery.PRODUTO AS PROD ON IV.ID_produto = PROD.ID
	WHERE IV.ID_venda=@idVenda

	SELECT @idContaCorr=CONTCORR.ID,@idCliente=VEN.Nif_cliente
	FROM grocery.VENDA AS VEN JOIN grocery.CONTA_CORRENTE AS CONTCORR ON VEN.Nif_cliente=CONTCORR.Nif_cliente

	UPDATE grocery.VENDA
	SET Valor=@total
	WHERE ID=@idVenda

	IF(@tipoPagamento=3)
		BEGIN
			INSERT INTO grocery.PAGAMENTO
			(ID_venda,Valor,ID_conta_corr,ID_tipo_pag)
			VALUES
			(@idVenda,@total,@idContaCorr,@tipoPagamento);
			EXEC grocery.sp_InsertOUTContaCorrente @idContaCorr, @total
		END
	ELSE
		BEGIN
			INSERT INTO grocery.PAGAMENTO
			(ID_venda,Valor,ID_tipo_pag)
			VALUES
			(@idVenda,@total,@tipoPagamento)
		END
END
GO

--receber pagamento para a conta corrente
CREATE PROC grocery.sp_InsertINContaCorrente
	@idCliente INT,
	@valor DECIMAL(10,2)
AS
	UPDATE grocery.CONTA_CORRENTE
	SET IN_cash = IN_cash + @valor
	WHERE Nif_cliente=@idCliente
GO

-----------------------------------------------------------
----------------------------UDF----------------------------
-----------------------------------------------------------
--UDF para pesquisar clientes pelo nome
CREATE FUNCTION grocery.UDF_PESQUISACLIENTESNOME
	(@nome VARCHAR(50))
RETURNS TABLE
AS
RETURN(
	SELECT CLIENTE.Nome AS Nome,CLIENTE.Nif AS NIF, CLIENTE.Morada AS Morada, CLIENTE.Contacto AS Contacto
	FROM grocery.CLIENTE 
	WHERE Cliente.Nome LIKE '%' + @nome + '%'
	)
GO

--UDF pesquisar conta corrente por nif cliente

CREATE FUNCTION grocery.UDF_PESQUISACONTACORRENTENIF
	(@nif INT)
RETURNS DECIMAL(10,2)
AS
BEGIN
	RETURN(
	SELECT Saldo
	FROM grocery.CONTA_CORRENTE 
	WHERE Nif_cliente=@nif
	)
END
GO

--UDF para pesquisar artigos pelo nome
CREATE FUNCTION grocery.UDF_PESQUISARARTIGOSNOME
	(@artigo VARCHAR(50))
RETURNS TABLE
AS
RETURN(
	SELECT * 
	FROM grocery.VIEW_PRODUTO_IVA 
	WHERE VIEW_PRODUTO_IVA.Artigo LIKE '%' + @artigo + '%'
	)
GO

--UDF para pesquisar artigos pelo nome na venda (menos informação)
CREATE FUNCTION grocery.UDF_PESQUISARARTIGOSNOMENAVENDA
	(@artigo VARCHAR(50))
RETURNS TABLE
AS
RETURN(
	SELECT * 
	FROM grocery.VIEW_PRODUTO_NAVENDA
	WHERE grocery.VIEW_PRODUTO_NAVENDA.Artigo LIKE '%' + @artigo + '%'
	)
GO

--UDF para ter a percentagem de lucro de um artigo
CREATE FUNCTION grocery.UDF_GETLUCROPORARTIGO
	(@idartigo INT)	
RETURNS INT
AS
BEGIN
	RETURN(
	SELECT Percent_lucro
	from grocery.PRODUTO
	where ID=@idartigo
	)
END
GO

--UDF para pesquisar fornecedores pelo nome
CREATE FUNCTION grocery.UDF_PESQUISAFORNECEDORESNOME
	(@nome VARCHAR(50))
RETURNS TABLE 
AS 
	RETURN(
	SELECT  FORNECEDOR.Nif AS NIF, FORNECEDOR.Contacto AS Contacto, FORNECEDOR.Nome AS Nome
	FROM grocery.FORNECEDOR
	WHERE FORNECEDOR.Nome LIKE '%' + @nome + '%'
	)
GO

-- UDF para pesquisar fornecedores pelo nome
CREATE FUNCTION grocery.UDF_PESQUISAFUNCIONARIOSNOME
	(@nome VARCHAR(50))
RETURNS TABLE
AS
	RETURN(
	Select FUNCIONARIO.Nome AS Nome, FUNCIONARIO.Nif AS NIF, FUNCIONARIO.Morada AS Morada, FUNCIONARIO.Contacto AS Contacto, FUNCIONARIO.CC AS CC, FUNCIONARIO.Salario AS Salario
	FROM grocery.FUNCIONARIO
	WHERE FUNCIONARIO.Nome LIKE '%' + @nome + '%'
	)
GO

--UDF para preencher datagrid com produtos de uma encomenda
CREATE FUNCTION grocery.UDF_PRODUTOS_DAENCOMENDA
	(@idEncomenda int)
RETURNS TABLE
AS
RETURN(
	SELECT prod.ID AS ID, prod.Nome AS Artigo, item.Quantidade AS Quantidade, item.Preco_forn AS PreçoFornecedor
	FROM grocery.ENCOMENDAS AS enc JOIN grocery.ITEM_ENCOMENDA AS item ON enc.ID=item.ID_encomenda JOIN grocery.PRODUTO AS prod ON prod.ID=item.ID_produto
	WHERE enc.ID = @idEncomenda
	)
GO

-----------------------------------------------------------
--------------------INSERTS NECESSARIOS--------------------
-----------------------------------------------------------

--Este insert é crucial, a conta corrente tem de ter o ID 3! Senão o SP que faz a gestão do pagamento não funciona
INSERT INTO grocery.TIPO_PAGAMENTO
VALUES
('Dinheiro'),
('Cheque'),
('Conta Corrente'),
('Multibanco');
GO


--Este não é necessario inserir pelo script porque da para o fazer pelo programa, mas desta forma já aparece logo os ivas onde é suposto
INSERT INTO grocery.IVA
VALUES
('Taxa Reduzida',6,1),
('Taxa Media',13,1),
('Taxa Normal',23,1),
('Taxa Normal',21,0);
GO

--lista de produtos com ID e Quantidade, acabou por nao ser utilizado por falta de tempo
CREATE TYPE grocery.ListaProdutos
AS TABLE
(
  idProd INT,
  quantidade INT
);
GO

