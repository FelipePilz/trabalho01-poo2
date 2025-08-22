CREATE DATABASE IF NOT EXISTS imobiliaria;
USE imobiliaria;

CREATE TABLE imoveis (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         endereco VARCHAR(255) NOT NULL,
                         tipo VARCHAR(100) NOT NULL,
                         quartos INT NOT NULL,
                         banheiros INT NOT NULL,
                         area DECIMAL(10, 2) NOT NULL,
                         valor_aluguel DECIMAL(10, 2) NOT NULL,
                         descricao TEXT,
                         garagem BOOLEAN DEFAULT FALSE,
                         condominio DECIMAL(10, 2) DEFAULT 0.00,
                         disponivel BOOLEAN DEFAULT TRUE,
                         data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE clientes (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          nome VARCHAR(100) NOT NULL,
                          cpf VARCHAR(14) UNIQUE NOT NULL,
                          telefone VARCHAR(20) NOT NULL,
                          email VARCHAR(100),
                          endereco VARCHAR(255),
                          data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE contratos (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           id_imovel INT NOT NULL,
                           id_cliente INT NOT NULL,
                           data_inicio DATE NOT NULL,
                           data_fim DATE NOT NULL,
                           valor DECIMAL(10, 2) NOT NULL,
                           ativo BOOLEAN DEFAULT TRUE,
                           observacoes TEXT,
                           data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           FOREIGN KEY (id_imovel) REFERENCES imoveis(id),
                           FOREIGN KEY (id_cliente) REFERENCES clientes(id),
                           INDEX idx_contratos_ativo (ativo),
                           INDEX idx_contratos_data_fim (data_fim)
);