CREATE
DATABASE IF NOT EXISTS imobiliaria;
USE
imobiliaria;

CREATE TABLE `imoveis`
(
    `id`            int          NOT NULL AUTO_INCREMENT,
    `endereco`      varchar(255) NOT NULL,
    `descricao`     text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `garagem`       tinyint(1) DEFAULT '0',
    `disponivel`    tinyint(1) DEFAULT '1',
    `data_cadastro` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
);

CREATE TABLE `clientes`
(
    `id`            int          NOT NULL AUTO_INCREMENT,
    `nome`          varchar(100) NOT NULL,
    `cpf`           varchar(14)  NOT NULL,
    `telefone`      varchar(20)  NOT NULL,
    `email`         varchar(100) DEFAULT NULL,
    `endereco`      varchar(255) DEFAULT NULL,
    `data_cadastro` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE KEY `cpf` (`cpf`)
);

CREATE TABLE `contratos`
(
    `id`            int            NOT NULL AUTO_INCREMENT,
    `id_imovel`     int            NOT NULL,
    `id_cliente`    int            NOT NULL,
    `data_inicio`   date           NOT NULL,
    `data_fim`      date           NOT NULL,
    `valor`         decimal(10, 2) NOT NULL,
    `ativo`         tinyint(1) DEFAULT '1',
    `observacoes`   text,
    `data_cadastro` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    KEY             `id_imovel` (`id_imovel`),
    KEY             `id_cliente` (`id_cliente`),
    KEY             `idx_contratos_ativo` (`ativo`),
    KEY             `idx_contratos_data_fim` (`data_fim`),
    CONSTRAINT `contratos_ibfk_1` FOREIGN KEY (`id_imovel`) REFERENCES `imoveis` (`id`),
    CONSTRAINT `contratos_ibfk_2` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id`)
);