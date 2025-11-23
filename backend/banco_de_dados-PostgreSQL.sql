/* 1. DROP DAS TABELAS */
DROP TABLE IF EXISTS VINCULA CASCADE;
DROP TABLE IF EXISTS TELEFONE CASCADE;
DROP TABLE IF EXISTS PESSOA_JURIDICA CASCADE;
DROP TABLE IF EXISTS PESSOA_FISICA CASCADE;
DROP TABLE IF EXISTS FORMULARIO CASCADE;
DROP TABLE IF EXISTS SERVICO CASCADE;
DROP TABLE IF EXISTS COMPROMISSO CASCADE;
DROP TABLE IF EXISTS CURRICULO CASCADE;
DROP TABLE IF EXISTS DOCUMENTO CASCADE;
DROP TABLE IF EXISTS CLIENTE CASCADE;
DROP TABLE IF EXISTS ADMINISTRADOR CASCADE;

/* 2. DROP DOS ENUM */
DROP TYPE IF EXISTS tipo_como_conheceu CASCADE;
DROP TYPE IF EXISTS tipo_estado_civil CASCADE;
DROP TYPE IF EXISTS tipo_compromisso CASCADE;
DROP TYPE IF EXISTS tipo_area CASCADE;

/* 3. CRIAÇÃO DOS ENUM */
CREATE TYPE tipo_como_conheceu AS ENUM ('Google', 'Instagram', 'Indicação', 'Outro');
CREATE TYPE tipo_estado_civil AS ENUM ('Solteiro(a)', 'Casado(a)', 'Divorciado(a)', 'Viúvo(a)');
CREATE TYPE tipo_compromisso AS ENUM ('orçamento', 'reunião', 'conferência de medidas', 'entrega/montagem');
CREATE TYPE tipo_area AS ENUM ('estágio', 'design de interiores', 'marcenaria');
CREATE TYPE tipo_status_servico AS ENUM ('em_espera', 'em_producao', 'concluido', 'cancelado');
CREATE TYPE tipo_status_compromisso AS ENUM ('a_confirmar', 'confirmado', 'realizado', 'cancelado'
);

/* 4. CRIAÇÃO DAS TABELAS */
CREATE TABLE ADMINISTRADOR (
    cpf_adm CHAR(11) PRIMARY KEY,
    nome VARCHAR(100),
    email VARCHAR(100),
    senha VARCHAR(100),
    logado BOOLEAN,
    UNIQUE (cpf_adm, email)
);

CREATE TABLE CLIENTE (
    id_cliente SERIAL PRIMARY KEY,
    nome VARCHAR(100),
    email VARCHAR(100),
    endereco VARCHAR(200)
);

CREATE TABLE DOCUMENTO (
    id_documento SERIAL PRIMARY KEY,
    nome VARCHAR(150),
    tipo VARCHAR(20),
    caminho VARCHAR(300)
);

CREATE TABLE CURRICULO (
    id_curriculo SERIAL PRIMARY KEY,
    nome VARCHAR(100),
    telefone VARCHAR(20),
    area_interesse tipo_area,
    pretensao_salarial DECIMAL(10,2),
    mensagem TEXT,
    como_conheceu tipo_como_conheceu,
    visualizado BOOLEAN,
    FK_DOCUMENTO_id_documento INT,
    email VARCHAR(100),

    FOREIGN KEY (FK_DOCUMENTO_id_documento)
        REFERENCES DOCUMENTO (id_documento)
        ON DELETE CASCADE
);

CREATE TABLE COMPROMISSO (
    id_compromisso SERIAL PRIMARY KEY,
    data_hora TIMESTAMP,
    tipo tipo_compromisso,
    funcionario VARCHAR(100),
    status tipo_status_compromisso,
    FK_CLIENTE_id_cliente INT,

    FOREIGN KEY (FK_CLIENTE_id_cliente)
        REFERENCES CLIENTE (id_cliente)
        ON DELETE CASCADE
);


CREATE TABLE SERVICO (
    id_servico SERIAL PRIMARY KEY,
    descricao TEXT,
    endereco_entrega VARCHAR(200),
    valor_base DECIMAL(10,2),
    status tipo_status_servico,
    FK_CLIENTE_id_cliente INT,
    FK_DOCUMENTO_id_documento INT,

    FOREIGN KEY (FK_CLIENTE_id_cliente)
        REFERENCES CLIENTE (id_cliente)
        ON DELETE CASCADE,

    FOREIGN KEY (FK_DOCUMENTO_id_documento)
        REFERENCES DOCUMENTO (id_documento)
        ON DELETE SET NULL
);


CREATE TABLE FORMULARIO (
    id_formulario SERIAL PRIMARY KEY,
    nome VARCHAR(100),
    telefone VARCHAR(20),
    email VARCHAR(100),
    mensagem TEXT,
    como_conheceu tipo_como_conheceu,
    visualizado BOOLEAN
);

CREATE TABLE PESSOA_FISICA (
    cpf CHAR(11) UNIQUE,
    profissao VARCHAR(100),
    estado_civil tipo_estado_civil,
    FK_CLIENTE_id_cliente INT PRIMARY KEY,

    FOREIGN KEY (FK_CLIENTE_id_cliente)
        REFERENCES CLIENTE (id_cliente)
        ON DELETE CASCADE
);

CREATE TABLE PESSOA_JURIDICA (
    cnpj CHAR(14) UNIQUE,
    FK_CLIENTE_id_cliente INT PRIMARY KEY,

    FOREIGN KEY (FK_CLIENTE_id_cliente)
        REFERENCES CLIENTE (id_cliente)
        ON DELETE CASCADE
);

CREATE TABLE TELEFONE (
    id_cliente INT NOT NULL,
    telefone VARCHAR(20),
    PRIMARY KEY (id_cliente, telefone),

    FOREIGN KEY (id_cliente)
        REFERENCES CLIENTE (id_cliente)
        ON DELETE CASCADE
);

CREATE TABLE VINCULA (
    fk_SERVICO_id_servico INT,
    fk_COMPROMISSO_id_compromisso INT,

    PRIMARY KEY (fk_SERVICO_id_servico, fk_COMPROMISSO_id_compromisso),

    FOREIGN KEY (fk_SERVICO_id_servico)
        REFERENCES SERVICO (id_servico)
        ON DELETE SET NULL,

    FOREIGN KEY (fk_COMPROMISSO_id_compromisso)
        REFERENCES COMPROMISSO (id_compromisso)
        ON DELETE SET NULL
);
