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

INSERT INTO `imoveis` (`endereco`, `descricao`, `garagem`, `disponivel`)
VALUES ('Rua das Flores, 123, São Paulo', 'Apartamento 2 quartos, 1 suíte, sala ampla, cozinha planejada.', 1, 0),
       ('Av. Brasil, 456, Rio de Janeiro', 'Casa térrea com 3 quartos, quintal grande e piscina.', 1, 0),
       ('Rua Amapá, 789, Belo Horizonte', 'Apartamento compacto, 1 quarto, ideal para solteiros.', 0, 1);

INSERT INTO `clientes` (`nome`, `cpf`, `telefone`, `email`, `endereco`)
VALUES ('João Silva', '123.456.789-00', '(11) 98765-4321', 'joao.silva@email.com', 'Rua das Palmeiras, 321, São Paulo'),
       ('Maria Oliveira', '987.654.321-00', '(21) 91234-5678', 'maria.oliveira@email.com',
        'Av. Copacabana, 1010, Rio de Janeiro'),
       ('Carlos Santos', '456.789.123-00', '(31) 99876-5432', 'carlos.santos@email.com',
        'Rua das Laranjeiras, 500, Belo Horizonte');

INSERT INTO `contratos` (`id_imovel`, `id_cliente`, `data_inicio`, `data_fim`, `valor`, `ativo`, `observacoes`)
VALUES (1, 1, '2025-08-01', '2026-07-31', 2500.00, 1, 'Pagamento mensal até o dia 5.'),
       (2, 2, '2025-09-01', '2026-08-31', 4500.00, 1, 'Contrato inclui manutenção da piscina.');