USE SQLdoc;

CREATE TABLE DOC_CARRO(
id_subModelo	INT PRIMARY KEY,
id_owner		INT,
combustivel		VARCHAR(15) NOT NULL,
n_portas		INT NOT NULL,
potenciaCV		INT NOT NULL,
potenciaKW		INT NOT NULL,
cilindrada		INT NOT NULL,
extra_info		VARCHAR(8000)
);

USE SQLdoc
CREATE TABLE DOC_PESSOA(
id_pessoa		INT PRIMARY KEY,
nome			VARCHAR(60) NOT NULL,
data_nascimento	DATE NOT NULL,
n_bi			INT NOT NULL UNIQUE,
n_contribuinte	INT NOT NULL UNIQUE,
morada			VARCHAR(100) NOT NULL,
IBAN			VARCHAR(25) NOT NULL UNIQUE
);