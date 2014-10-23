drop sequence USUARIO_SEQ_ID;
drop sequence ACAO_SEQ_ID;
drop table vizinhos;
drop table troca_cartas;
drop table ataque;
drop table distribuicao;
drop table remanejamento;
drop table troca;
drop table territorioinicial;
drop table cartas;
drop table usuario;
drop table territorio;
drop table continente;
drop table objetivo;

CREATE TABLE OBJETIVO
  (
    NR_ID_OBJ    INTEGER NOT NULL ,
    NM_DESCRICAO VARCHAR2 (200 CHAR) NOT NULL
  ) ;
ALTER TABLE OBJETIVO ADD CONSTRAINT OBJETIVO_PK PRIMARY KEY ( NR_ID_OBJ ) ;

CREATE TABLE CONTINENTE
  (
    NR_ID_CON INTEGER NOT NULL ,
    NM_NOME   VARCHAR2 (100 CHAR) NOT NULL
  ) ;
ALTER TABLE CONTINENTE ADD CONSTRAINT CONTINENTE_PK PRIMARY KEY ( NR_ID_CON ) ;

CREATE TABLE TERRITORIO
  (
    NR_ID_TER    INTEGER NOT NULL ,
    NM_NOME      VARCHAR2 (100 CHAR) NOT NULL ,
    NR_NUM_PECAS INTEGER NOT NULL ,
    NR_ID_USU    INTEGER ,
    NR_ID_CON    INTEGER NOT NULL
  ) ;
ALTER TABLE TERRITORIO ADD CONSTRAINT TERRITORIO_PK PRIMARY KEY ( NR_ID_TER ) ;

CREATE TABLE USUARIO
  (
    NR_ID_USU INTEGER NOT NULL ,
    NM_NOME   VARCHAR2 (150 CHAR) NOT NULL ,
    NM_COR    VARCHAR2 (20 CHAR) ,
    NR_ID_OBJ INTEGER
  ) ;
ALTER TABLE USUARIO ADD CONSTRAINT USUARIO_PK PRIMARY KEY ( NR_ID_USU ) ;

CREATE TABLE VIZINHOS
  (
    NR_ID_TER     INTEGER NOT NULL ,
    NR_ID_TER_VIZ INTEGER NOT NULL
  ) ;
ALTER TABLE VIZINHOS ADD CONSTRAINT VIZINHOS_PK PRIMARY KEY ( NR_ID_TER, NR_ID_TER_VIZ ) ;
  
CREATE TABLE CARTAS
  (
	NR_ID_CARTA INTEGER NOT NULL ,
    NR_ID_TER   INTEGER ,
    NM_SIMBOLO  VARCHAR2 (50 CHAR) NOT NULL ,
    NR_ID_USU   INTEGER,
	OB_CURINGA  CHAR (20) NOT NULL
  ) ;
ALTER TABLE CARTAS ADD CONSTRAINT CARTAS_PK PRIMARY KEY ( NR_ID_CARTA ) ;

CREATE TABLE ATAQUE
  (
    NR_ID_ACAO    INTEGER NOT NULL ,
    NR_ID_USU     INTEGER NOT NULL ,
    NR_ID_TER_ATA INTEGER NOT NULL ,
    NR_ID_TER_DEF INTEGER NOT NULL ,
    NR_ATAQUE     INTEGER NOT NULL ,
    NR_DEFESA     INTEGER NOT NULL
  ) ;
ALTER TABLE ATAQUE ADD CONSTRAINT ATAQUE_PK PRIMARY KEY ( NR_ID_ACAO ) ;

CREATE TABLE DISTRIBUICAO
  (
    NR_ID_ACAO INTEGER NOT NULL ,
    NR_ID_USU  INTEGER NOT NULL ,
    NR_ID_TER  INTEGER NOT NULL ,
    NR_QTD     INTEGER NOT NULL
  ) ;
ALTER TABLE DISTRIBUICAO ADD CONSTRAINT DISTRIBUICAO_PK PRIMARY KEY ( NR_ID_ACAO ) ;

CREATE TABLE REMANEJAMENTO
  (
    NR_ID_ACAO    INTEGER NOT NULL ,
    NR_ID_USU     INTEGER NOT NULL ,
    NR_ID_TER     INTEGER NOT NULL ,
    NR_ID_TER_REM INTEGER NOT NULL ,
    NR_QTD        INTEGER NOT NULL
  ) ;
ALTER TABLE REMANEJAMENTO ADD CONSTRAINT REMANEJAMENTO_PK PRIMARY KEY ( NR_ID_ACAO ) ;

CREATE TABLE TROCA
  (
    NR_ID_ACAO INTEGER NOT NULL ,
	NR_ID_USU INTEGER NOT NULL
  ) ;
ALTER TABLE TROCA ADD CONSTRAINT TROCA_PK PRIMARY KEY ( NR_ID_ACAO ) ;

CREATE TABLE TROCA_CARTAS
  (
    NR_ID_ACAO  INTEGER NOT NULL ,
    NR_ID_CARTA INTEGER NOT NULL
  ) ;
ALTER TABLE TROCA_CARTAS ADD CONSTRAINT TROCA_CARTAS_PK PRIMARY KEY ( NR_ID_ACAO, NR_ID_CARTA ) ;

CREATE TABLE TERRITORIOINICIAL
  (
    NR_ID_TER INTEGER NOT NULL ,
    NR_ID_USU INTEGER NOT NULL
  ) ;
ALTER TABLE TERRITORIOINICIAL ADD CONSTRAINT TERRITORIOINICIAL_PK PRIMARY KEY ( NR_ID_TER ) ;


ALTER TABLE ATAQUE ADD CONSTRAINT ATAQUE_TERRITORIO_FK FOREIGN KEY ( NR_ID_TER_ATA ) REFERENCES TERRITORIO ( NR_ID_TER ) ;

ALTER TABLE ATAQUE ADD CONSTRAINT ATAQUE_TERRITORIO_FKv1 FOREIGN KEY ( NR_ID_TER_DEF ) REFERENCES TERRITORIO ( NR_ID_TER ) ;

ALTER TABLE ATAQUE ADD CONSTRAINT ATAQUE_USUARIO_FK FOREIGN KEY ( NR_ID_USU ) REFERENCES USUARIO ( NR_ID_USU ) ;

ALTER TABLE CARTAS ADD CONSTRAINT CARTAS_TERRITORIO_FK FOREIGN KEY ( NR_ID_TER ) REFERENCES TERRITORIO ( NR_ID_TER ) ;

ALTER TABLE CARTAS ADD CONSTRAINT CARTAS_USUARIO_FK FOREIGN KEY ( NR_ID_USU ) REFERENCES USUARIO ( NR_ID_USU ) ;

ALTER TABLE DISTRIBUICAO ADD CONSTRAINT DISTRIBUICAO_TERRITORIO_FK FOREIGN KEY ( NR_ID_TER ) REFERENCES TERRITORIO ( NR_ID_TER ) ;

ALTER TABLE DISTRIBUICAO ADD CONSTRAINT DISTRIBUICAO_USUARIO_FK FOREIGN KEY ( NR_ID_USU ) REFERENCES USUARIO ( NR_ID_USU ) ;

ALTER TABLE TROCA_CARTAS ADD CONSTRAINT TROCA_CARTAS_TROCA_FK FOREIGN KEY ( NR_ID_ACAO ) REFERENCES TROCA ( NR_ID_ACAO ) ;

ALTER TABLE TROCA_CARTAS ADD CONSTRAINT TROCA_CARTAS_CARTAS_FK FOREIGN KEY ( NR_ID_CARTA ) REFERENCES CARTAS ( NR_ID_CARTA ) ;

ALTER TABLE VIZINHOS ADD CONSTRAINT VIZINHOS_TERRITORIO_FK FOREIGN KEY ( NR_ID_TER ) REFERENCES TERRITORIO ( NR_ID_TER ) ;

ALTER TABLE VIZINHOS ADD CONSTRAINT VIZINHOS_TERRITORIO_FKv1 FOREIGN KEY ( NR_ID_TER_VIZ ) REFERENCES TERRITORIO ( NR_ID_TER ) ;

ALTER TABLE REMANEJAMENTO ADD CONSTRAINT REMANEJAMENTO_TERRITORIO_FK FOREIGN KEY ( NR_ID_TER ) REFERENCES TERRITORIO ( NR_ID_TER ) ;

ALTER TABLE REMANEJAMENTO ADD CONSTRAINT REMANEJAMENTO_TERRITORIO_FKv1 FOREIGN KEY ( NR_ID_TER_REM ) REFERENCES TERRITORIO ( NR_ID_TER ) ;

ALTER TABLE REMANEJAMENTO ADD CONSTRAINT REMANEJAMENTO_USUARIO_FK FOREIGN KEY ( NR_ID_USU ) REFERENCES USUARIO ( NR_ID_USU ) ;

ALTER TABLE TERRITORIOINICIAL ADD CONSTRAINT TERINICIAL_TERRITORIO_FK FOREIGN KEY ( NR_ID_TER ) REFERENCES TERRITORIO ( NR_ID_TER ) ;

ALTER TABLE TERRITORIOINICIAL ADD CONSTRAINT TERINICIAL_USUARIO_FK FOREIGN KEY ( NR_ID_USU ) REFERENCES USUARIO ( NR_ID_USU ) ;

ALTER TABLE TERRITORIO ADD CONSTRAINT TERRITORIO_CONTINENTE_FK FOREIGN KEY ( NR_ID_CON ) REFERENCES CONTINENTE ( NR_ID_CON ) ;

ALTER TABLE TERRITORIO ADD CONSTRAINT TERRITORIO_USUARIO_FK FOREIGN KEY ( NR_ID_USU ) REFERENCES USUARIO ( NR_ID_USU ) ;

ALTER TABLE TROCA ADD CONSTRAINT TROCA_USUARIO_FK FOREIGN KEY ( NR_ID_USU ) REFERENCES USUARIO ( NR_ID_USU ) ;

ALTER TABLE USUARIO ADD CONSTRAINT USUARIO_OBJETIVO_FK FOREIGN KEY ( NR_ID_OBJ ) REFERENCES OBJETIVO ( NR_ID_OBJ ) ;


CREATE SEQUENCE USUARIO_SEQ_ID
  START WITH 1
  MAXVALUE 9999999999999999999999999
  MINVALUE 1
  NOCYCLE
  NOCACHE
  NOORDER;

CREATE SEQUENCE ACAO_SEQ_ID
  START WITH 1
  MAXVALUE 9999999999999999999999999
  MINVALUE 1
  NOCYCLE
  NOCACHE
  NOORDER;

  
insert into OBJETIVO (NR_ID_OBJ, NM_DESCRICAO) values (1,'Conquistar na totalidade a EUROPA, a OCEANIA e mais um terceiro');
insert into OBJETIVO (NR_ID_OBJ, NM_DESCRICAO) values (2,'Conquistar na totalidade a ASIA e a AMERICA DO SUL.');
insert into OBJETIVO (NR_ID_OBJ, NM_DESCRICAO) values (3,'Conquistar na totalidade a EUROPA, a AMERICA DO SUL e mais um terceiro.');
insert into OBJETIVO (NR_ID_OBJ, NM_DESCRICAO) values (4,'Conquistar 18 TERRITORIOS e ocupar cada um deles com pelo menos dois exercitos.');
insert into OBJETIVO (NR_ID_OBJ, NM_DESCRICAO) values (5,'Conquistar na totalidade a ASIA e a AFRICA.');
insert into OBJETIVO (NR_ID_OBJ, NM_DESCRICAO) values (6,'Conquistar na totalidade a AMERICA DO NORTE e a AFRICA.');
insert into OBJETIVO (NR_ID_OBJ, NM_DESCRICAO) values (7,'Conquistar 24 TERRITORIOS à sua escolha.');
insert into OBJETIVO (NR_ID_OBJ, NM_DESCRICAO) values (8,'Conquistar na totalidade a AMERICA DO NORTE e a OCEANIA.');
insert into OBJETIVO (NR_ID_OBJ, NM_DESCRICAO) values (9,'Destruir totalmente OS EXERCITOS AZUIS.');
insert into OBJETIVO (NR_ID_OBJ, NM_DESCRICAO) values (10,'Destruir totalmente OS EXERCITOS AMARELOS.');
insert into OBJETIVO (NR_ID_OBJ, NM_DESCRICAO) values (11,'Destruir totalmente OS EXERCITOS VERMELHOS.');
insert into OBJETIVO (NR_ID_OBJ, NM_DESCRICAO) values (12,'Destruir totalmente OS EXERCITOS PRETOS.');
insert into OBJETIVO (NR_ID_OBJ, NM_DESCRICAO) values (13,'Destruir totalmente OS EXERCITOS BRANCO.');
insert into OBJETIVO (NR_ID_OBJ, NM_DESCRICAO) values (14,'Destruir totalmente OS EXERCITOS VERDES.');

insert into CONTINENTE (NR_ID_CON, NM_NOME) values (1, 'America do Sul');
insert into CONTINENTE (NR_ID_CON, NM_NOME) values (2, 'America do Norte');
insert into CONTINENTE (NR_ID_CON, NM_NOME) values (3, 'Europa');
insert into CONTINENTE (NR_ID_CON, NM_NOME) values (4, 'Africa');
insert into CONTINENTE (NR_ID_CON, NM_NOME) values (5, 'Asia');
insert into CONTINENTE (NR_ID_CON, NM_NOME) values (6, 'Oceania');

insert into TERRITORIO(NR_ID_TER, NM_NOME, NR_NUM_PECAS, NR_ID_CON) values(1,'Brasil', 0, 1);
insert into TERRITORIO(NR_ID_TER, NM_NOME, NR_NUM_PECAS, NR_ID_CON) values(2,'Argentina-Uruguai', 0, 1);
insert into TERRITORIO(NR_ID_TER, NM_NOME, NR_NUM_PECAS, NR_ID_CON) values(3,'Chile-Bolivia-Peru', 0, 1);
insert into TERRITORIO(NR_ID_TER, NM_NOME, NR_NUM_PECAS, NR_ID_CON) values(4,'Colombia-Venezuela', 0, 1);
insert into TERRITORIO(NR_ID_TER, NM_NOME, NR_NUM_PECAS, NR_ID_CON) values(5,'Mexico', 0, 2);
insert into TERRITORIO(NR_ID_TER, NM_NOME, NR_NUM_PECAS, NR_ID_CON) values(6,'Nova York', 0, 2);
insert into TERRITORIO(NR_ID_TER, NM_NOME, NR_NUM_PECAS, NR_ID_CON) values(7,'California', 0, 2);
insert into TERRITORIO(NR_ID_TER, NM_NOME, NR_NUM_PECAS, NR_ID_CON) values(8,'Ottawa', 0, 2);
insert into TERRITORIO(NR_ID_TER, NM_NOME, NR_NUM_PECAS, NR_ID_CON) values(9,'Vancouver', 0, 2);
insert into TERRITORIO(NR_ID_TER, NM_NOME, NR_NUM_PECAS, NR_ID_CON) values(10,'Labrador', 0, 2);
insert into TERRITORIO(NR_ID_TER, NM_NOME, NR_NUM_PECAS, NR_ID_CON) values(11,'Alaska', 0, 2);
insert into TERRITORIO(NR_ID_TER, NM_NOME, NR_NUM_PECAS, NR_ID_CON) values(12,'Mackenzie', 0, 2);
insert into TERRITORIO(NR_ID_TER, NM_NOME, NR_NUM_PECAS, NR_ID_CON) values(13,'Groelândia', 0, 2);
insert into TERRITORIO(NR_ID_TER, NM_NOME, NR_NUM_PECAS, NR_ID_CON) values(14,'Islandia', 0, 3);
insert into TERRITORIO(NR_ID_TER, NM_NOME, NR_NUM_PECAS, NR_ID_CON) values(15,'Inglaterra', 0, 3);
insert into TERRITORIO(NR_ID_TER, NM_NOME, NR_NUM_PECAS, NR_ID_CON) values(16,'Suecia', 0, 3);
insert into TERRITORIO(NR_ID_TER, NM_NOME, NR_NUM_PECAS, NR_ID_CON) values(17,'Moscou', 0, 3);
insert into TERRITORIO(NR_ID_TER, NM_NOME, NR_NUM_PECAS, NR_ID_CON) values(18,'Polonia-Iugoslavia', 0, 3);
insert into TERRITORIO(NR_ID_TER, NM_NOME, NR_NUM_PECAS, NR_ID_CON) values(19,'Portugal-Espanha', 0, 3);
insert into TERRITORIO(NR_ID_TER, NM_NOME, NR_NUM_PECAS, NR_ID_CON) values(20,'Franca', 0, 3);
insert into TERRITORIO(NR_ID_TER, NM_NOME, NR_NUM_PECAS, NR_ID_CON) values(21,'Argelia-Nigeria', 0, 4);
insert into TERRITORIO(NR_ID_TER, NM_NOME, NR_NUM_PECAS, NR_ID_CON) values(22,'Egito', 0, 4);
insert into TERRITORIO(NR_ID_TER, NM_NOME, NR_NUM_PECAS, NR_ID_CON) values(23,'Sudao', 0, 4);
insert into TERRITORIO(NR_ID_TER, NM_NOME, NR_NUM_PECAS, NR_ID_CON) values(24,'Congo', 0, 4);
insert into TERRITORIO(NR_ID_TER, NM_NOME, NR_NUM_PECAS, NR_ID_CON) values(25,'Africa do Sul', 0, 4);
insert into TERRITORIO(NR_ID_TER, NM_NOME, NR_NUM_PECAS, NR_ID_CON) values(26,'Madagascar', 0, 4);
insert into TERRITORIO(NR_ID_TER, NM_NOME, NR_NUM_PECAS, NR_ID_CON) values(27,'Oriente Medio', 0, 5);
insert into TERRITORIO(NR_ID_TER, NM_NOME, NR_NUM_PECAS, NR_ID_CON) values(28,'Aral', 0, 5);
insert into TERRITORIO(NR_ID_TER, NM_NOME, NR_NUM_PECAS, NR_ID_CON) values(29,'Omsk', 0, 5);
insert into TERRITORIO(NR_ID_TER, NM_NOME, NR_NUM_PECAS, NR_ID_CON) values(30,'Dudinka', 0, 5);
insert into TERRITORIO(NR_ID_TER, NM_NOME, NR_NUM_PECAS, NR_ID_CON) values(31,'Siberia', 0, 5);
insert into TERRITORIO(NR_ID_TER, NM_NOME, NR_NUM_PECAS, NR_ID_CON) values(32,'Tchita', 0, 5);
insert into TERRITORIO(NR_ID_TER, NM_NOME, NR_NUM_PECAS, NR_ID_CON) values(33,'Mongolia', 0, 5);
insert into TERRITORIO(NR_ID_TER, NM_NOME, NR_NUM_PECAS, NR_ID_CON) values(34,'China', 0, 5);
insert into TERRITORIO(NR_ID_TER, NM_NOME, NR_NUM_PECAS, NR_ID_CON) values(35,'India', 0, 5);
insert into TERRITORIO(NR_ID_TER, NM_NOME, NR_NUM_PECAS, NR_ID_CON) values(36,'Vietna', 0, 5);
insert into TERRITORIO(NR_ID_TER, NM_NOME, NR_NUM_PECAS, NR_ID_CON) values(37,'Japao', 0, 5);
insert into TERRITORIO(NR_ID_TER, NM_NOME, NR_NUM_PECAS, NR_ID_CON) values(38,'Vladivostok', 0, 5);
insert into TERRITORIO(NR_ID_TER, NM_NOME, NR_NUM_PECAS, NR_ID_CON) values(39,'Sumaria', 0, 6);
insert into TERRITORIO(NR_ID_TER, NM_NOME, NR_NUM_PECAS, NR_ID_CON) values(40,'Borneo', 0, 6);
insert into TERRITORIO(NR_ID_TER, NM_NOME, NR_NUM_PECAS, NR_ID_CON) values(41,'Nova Guine', 0, 6);
insert into TERRITORIO(NR_ID_TER, NM_NOME, NR_NUM_PECAS, NR_ID_CON) values(42,'Australia', 0, 6);

insert into CARTAS(NR_ID_CARTA, NR_ID_TER, NM_SIMBOLO, OB_CURINGA) values(1, 1, 'Quadrado', 'false');
insert into CARTAS(NR_ID_CARTA, NR_ID_TER, NM_SIMBOLO, OB_CURINGA) values(2, 2, 'Bola', 'false');
insert into CARTAS(NR_ID_CARTA, NR_ID_TER, NM_SIMBOLO, OB_CURINGA) values(3, 3, 'Triangulo', 'false');
insert into CARTAS(NR_ID_CARTA, NR_ID_TER, NM_SIMBOLO, OB_CURINGA) values(4, 4, 'Quadrado', 'false');
insert into CARTAS(NR_ID_CARTA, NR_ID_TER, NM_SIMBOLO, OB_CURINGA) values(5, 5, 'Bola', 'false');
insert into CARTAS(NR_ID_CARTA, NR_ID_TER, NM_SIMBOLO, OB_CURINGA) values(6, 6, 'Triangulo', 'false');
insert into CARTAS(NR_ID_CARTA, NR_ID_TER, NM_SIMBOLO, OB_CURINGA) values(7, 7, 'Quadrado', 'false');
insert into CARTAS(NR_ID_CARTA, NR_ID_TER, NM_SIMBOLO, OB_CURINGA) values(8, 8, 'Bola', 'false');
insert into CARTAS(NR_ID_CARTA, NR_ID_TER, NM_SIMBOLO, OB_CURINGA) values(9, 9, 'Triangulo', 'false');
insert into CARTAS(NR_ID_CARTA, NR_ID_TER, NM_SIMBOLO, OB_CURINGA) values(10, 10, 'Quadrado', 'false');
insert into CARTAS(NR_ID_CARTA, NR_ID_TER, NM_SIMBOLO, OB_CURINGA) values(11, 11, 'Bola', 'false');
insert into CARTAS(NR_ID_CARTA, NR_ID_TER, NM_SIMBOLO, OB_CURINGA) values(12, 12, 'Triangulo', 'false');
insert into CARTAS(NR_ID_CARTA, NR_ID_TER, NM_SIMBOLO, OB_CURINGA) values(13, 13, 'Quadrado', 'false');
insert into CARTAS(NR_ID_CARTA, NR_ID_TER, NM_SIMBOLO, OB_CURINGA) values(14, 14, 'Bola', 'false');
insert into CARTAS(NR_ID_CARTA, NR_ID_TER, NM_SIMBOLO, OB_CURINGA) values(15, 15, 'Triangulo', 'false');
insert into CARTAS(NR_ID_CARTA, NR_ID_TER, NM_SIMBOLO, OB_CURINGA) values(16, 16, 'Quadrado', 'false');
insert into CARTAS(NR_ID_CARTA, NR_ID_TER, NM_SIMBOLO, OB_CURINGA) values(17, 17, 'Bola', 'false');
insert into CARTAS(NR_ID_CARTA, NR_ID_TER, NM_SIMBOLO, OB_CURINGA) values(18, 18, 'Triangulo', 'false');
insert into CARTAS(NR_ID_CARTA, NR_ID_TER, NM_SIMBOLO, OB_CURINGA) values(19, 19, 'Quadrado', 'false');
insert into CARTAS(NR_ID_CARTA, NR_ID_TER, NM_SIMBOLO, OB_CURINGA) values(20, 20, 'Bola', 'false');
insert into CARTAS(NR_ID_CARTA, NR_ID_TER, NM_SIMBOLO, OB_CURINGA) values(21, 21, 'Triangulo', 'false');
insert into CARTAS(NR_ID_CARTA, NR_ID_TER, NM_SIMBOLO, OB_CURINGA) values(22, 22, 'Quadrado', 'false');
insert into CARTAS(NR_ID_CARTA, NR_ID_TER, NM_SIMBOLO, OB_CURINGA) values(23, 23, 'Bola', 'false');
insert into CARTAS(NR_ID_CARTA, NR_ID_TER, NM_SIMBOLO, OB_CURINGA) values(24, 24, 'Triangulo', 'false');
insert into CARTAS(NR_ID_CARTA, NR_ID_TER, NM_SIMBOLO, OB_CURINGA) values(25, 25, 'Quadrado', 'false');
insert into CARTAS(NR_ID_CARTA, NR_ID_TER, NM_SIMBOLO, OB_CURINGA) values(26, 26, 'Bola', 'false');
insert into CARTAS(NR_ID_CARTA, NR_ID_TER, NM_SIMBOLO, OB_CURINGA) values(27, 27, 'Triangulo', 'false');
insert into CARTAS(NR_ID_CARTA, NR_ID_TER, NM_SIMBOLO, OB_CURINGA) values(28, 28, 'Quadrado', 'false');
insert into CARTAS(NR_ID_CARTA, NR_ID_TER, NM_SIMBOLO, OB_CURINGA) values(29, 29, 'Bola', 'false');
insert into CARTAS(NR_ID_CARTA, NR_ID_TER, NM_SIMBOLO, OB_CURINGA) values(30, 30, 'Triangulo', 'false');
insert into CARTAS(NR_ID_CARTA, NR_ID_TER, NM_SIMBOLO, OB_CURINGA) values(31, 31, 'Quadrado', 'false');
insert into CARTAS(NR_ID_CARTA, NR_ID_TER, NM_SIMBOLO, OB_CURINGA) values(32, 32, 'Bola', 'false');
insert into CARTAS(NR_ID_CARTA, NR_ID_TER, NM_SIMBOLO, OB_CURINGA) values(33, 33, 'Triangulo', 'false');
insert into CARTAS(NR_ID_CARTA, NR_ID_TER, NM_SIMBOLO, OB_CURINGA) values(34, 34, 'Quadrado', 'false');
insert into CARTAS(NR_ID_CARTA, NR_ID_TER, NM_SIMBOLO, OB_CURINGA) values(35, 35, 'Bola', 'false');
insert into CARTAS(NR_ID_CARTA, NR_ID_TER, NM_SIMBOLO, OB_CURINGA) values(36, 36, 'Triangulo', 'false');
insert into CARTAS(NR_ID_CARTA, NR_ID_TER, NM_SIMBOLO, OB_CURINGA) values(37, 37, 'Quadrado', 'false');
insert into CARTAS(NR_ID_CARTA, NR_ID_TER, NM_SIMBOLO, OB_CURINGA) values(38, 38, 'Bola', 'false');
insert into CARTAS(NR_ID_CARTA, NR_ID_TER, NM_SIMBOLO, OB_CURINGA) values(39, 39, 'Triangulo', 'false');
insert into CARTAS(NR_ID_CARTA, NR_ID_TER, NM_SIMBOLO, OB_CURINGA) values(40, 40, 'Quadrado', 'false');
insert into CARTAS(NR_ID_CARTA, NR_ID_TER, NM_SIMBOLO, OB_CURINGA) values(41, 41, 'Bola', 'false');
insert into CARTAS(NR_ID_CARTA, NR_ID_TER, NM_SIMBOLO, OB_CURINGA) values(42, 42, 'Triangulo', 'false');
insert into CARTAS(NR_ID_CARTA, NM_SIMBOLO, OB_CURINGA) values(43, 'Quadrado-Bola-Triangulo', 'true');
insert into CARTAS(NR_ID_CARTA, NM_SIMBOLO, OB_CURINGA) values(44, 'Quadrado-Bola-Triangulo', 'true');

insert into VIZINHOS(NR_ID_TER, NR_ID_TER_VIZ) values (1, 2);
insert into VIZINHOS(NR_ID_TER, NR_ID_TER_VIZ) values (1, 3);
insert into VIZINHOS(NR_ID_TER, NR_ID_TER_VIZ) values (1, 4);
insert into VIZINHOS(NR_ID_TER, NR_ID_TER_VIZ) values (1, 21);
insert into VIZINHOS(NR_ID_TER, NR_ID_TER_VIZ) values (2, 1);
insert into VIZINHOS(NR_ID_TER, NR_ID_TER_VIZ) values (2, 3);
insert into VIZINHOS(NR_ID_TER, NR_ID_TER_VIZ) values (3, 1);
insert into VIZINHOS(NR_ID_TER, NR_ID_TER_VIZ) values (3, 2);
insert into VIZINHOS(NR_ID_TER, NR_ID_TER_VIZ) values (3, 4);
insert into VIZINHOS(NR_ID_TER, NR_ID_TER_VIZ) values (4, 1);
insert into VIZINHOS(NR_ID_TER, NR_ID_TER_VIZ) values (4, 3);
insert into VIZINHOS(NR_ID_TER, NR_ID_TER_VIZ) values (4, 5);
insert into VIZINHOS(NR_ID_TER, NR_ID_TER_VIZ) values (5, 4);
insert into VIZINHOS(NR_ID_TER, NR_ID_TER_VIZ) values (5, 6);
insert into VIZINHOS(NR_ID_TER, NR_ID_TER_VIZ) values (5, 7);
insert into VIZINHOS(NR_ID_TER, NR_ID_TER_VIZ) values (6, 5);
insert into VIZINHOS(NR_ID_TER, NR_ID_TER_VIZ) values (6, 7);
insert into VIZINHOS(NR_ID_TER, NR_ID_TER_VIZ) values (6, 8);
insert into VIZINHOS(NR_ID_TER, NR_ID_TER_VIZ) values (6, 10);
insert into VIZINHOS(NR_ID_TER, NR_ID_TER_VIZ) values (7, 5);
insert into VIZINHOS(NR_ID_TER, NR_ID_TER_VIZ) values (7, 6);
insert into VIZINHOS(NR_ID_TER, NR_ID_TER_VIZ) values (7, 8);
insert into VIZINHOS(NR_ID_TER, NR_ID_TER_VIZ) values (7, 9);
insert into VIZINHOS(NR_ID_TER, NR_ID_TER_VIZ) values (8, 6);
insert into VIZINHOS(NR_ID_TER, NR_ID_TER_VIZ) values (8, 7);
insert into VIZINHOS(NR_ID_TER, NR_ID_TER_VIZ) values (8, 9);
insert into VIZINHOS(NR_ID_TER, NR_ID_TER_VIZ) values (8, 10);
insert into VIZINHOS(NR_ID_TER, NR_ID_TER_VIZ) values (8, 12);
insert into VIZINHOS(NR_ID_TER, NR_ID_TER_VIZ) values (9, 7);
insert into VIZINHOS(NR_ID_TER, NR_ID_TER_VIZ) values (9, 8);
insert into VIZINHOS(NR_ID_TER, NR_ID_TER_VIZ) values (9, 11);
insert into VIZINHOS(NR_ID_TER, NR_ID_TER_VIZ) values (9, 12);
insert into VIZINHOS(NR_ID_TER, NR_ID_TER_VIZ) values (10, 6);
insert into VIZINHOS(NR_ID_TER, NR_ID_TER_VIZ) values (10, 8);
insert into VIZINHOS(NR_ID_TER, NR_ID_TER_VIZ) values (10, 13);
insert into VIZINHOS(NR_ID_TER, NR_ID_TER_VIZ) values (11, 9);
insert into VIZINHOS(NR_ID_TER, NR_ID_TER_VIZ) values (11, 12);
insert into VIZINHOS(NR_ID_TER, NR_ID_TER_VIZ) values (11, 38);
insert into VIZINHOS(NR_ID_TER, NR_ID_TER_VIZ) values (12, 8);
insert into VIZINHOS(NR_ID_TER, NR_ID_TER_VIZ) values (12, 9);
insert into VIZINHOS(NR_ID_TER, NR_ID_TER_VIZ) values (12, 11);
insert into VIZINHOS(NR_ID_TER, NR_ID_TER_VIZ) values (12, 13);
insert into VIZINHOS(NR_ID_TER, NR_ID_TER_VIZ) values (13, 10);
insert into VIZINHOS(NR_ID_TER, NR_ID_TER_VIZ) values (13, 12);
insert into VIZINHOS(NR_ID_TER, NR_ID_TER_VIZ) values (13, 14);
--falta Europa, Africa, Asia, Oceania


--insert into VIZINHOS(NR_ID_TER, NR_ID_TER_VIZ) values (1, );