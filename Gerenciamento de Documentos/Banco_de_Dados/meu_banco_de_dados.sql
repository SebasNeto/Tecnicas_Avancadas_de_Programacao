CREATE DATABASE meu_banco_de_dados;
USE meu_banco_de_dados;

CREATE TABLE tabela_cargos (
    Id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    Nome_cargo VARCHAR(50) NOT NULL,
    Created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (Id)
);

CREATE TABLE tabela_funcionarios (
    nome VARCHAR(50) NOT NULL,
    sobrenome VARCHAR(50) NOT NULL,
    data_nascimento VARCHAR(50),
    email VARCHAR(50) NOT NULL,
    cargo VARCHAR(50),
    salario DECIMAL(10,0),
    id INT NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (id)
);

CREATE TABLE documentos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    referencia VARCHAR(255),
    data_arquivamento DATE,
    interessado_id INT,
    tipo VARCHAR(255),
    descricao TEXT,
    armazenamento VARCHAR(255),
    localizacao VARCHAR(255)
);

SELECT * FROM tabela_cargos;
SELECT * FROM tabela_funcionarios;
SELECT * FROM documentos;

