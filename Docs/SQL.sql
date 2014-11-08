drop sequence JOGO_SEQ_ID;
drop sequence USUARIO_SEQ_ID;
drop table CARTA;
drop table CONTINENTE;
drop table JOGO;
drop table OBJETIVO;
drop table TERRITORIO;
drop table USUARIO;
drop table VIZINHO;

CREATE TABLE OBJETIVO
  (
    ID_OBJETIVO  INTEGER NOT NULL ,
    NM_DESCRICAO VARCHAR2 (200 CHAR) NOT NULL
  ) ;
ALTER TABLE OBJETIVO ADD CONSTRAINT OBJETIVO_PK PRIMARY KEY ( ID_OBJETIVO ) ;

CREATE TABLE CONTINENTE
  (
    ID_CONTINENTE INTEGER NOT NULL ,
    NM_NOME       VARCHAR2 (100 CHAR) NOT NULL
  ) ;
ALTER TABLE CONTINENTE ADD CONSTRAINT CONTINENTE_PK PRIMARY KEY ( ID_CONTINENTE
) ;

CREATE TABLE JOGO
  (
    ID_JOGO INTEGER NOT NULL ,
    NM_NOME VARCHAR2 (100) NOT NULL,
    NM_TIPO VARCHAR2(100) NOT NULL
  ) ;
ALTER TABLE JOGO ADD CONSTRAINT JOGO_PK PRIMARY KEY ( ID_JOGO ) ;

CREATE TABLE CARTA
  (
    ID_CARTA      INTEGER NOT NULL ,
    NM_SIMBOLO    VARCHAR2 (30 CHAR) NOT NULL ,
    ID_USUARIO    INTEGER ,
    BO_CORINGA    NUMBER(3) NOT NULL
  ) ;
ALTER TABLE CARTA ADD CONSTRAINT CARTA_PK PRIMARY KEY ( ID_CARTA ) ;

CREATE TABLE TERRITORIO
  (
    ID_TERRITORIO INTEGER NOT NULL ,
    NM_NOME       VARCHAR2 (100 CHAR) NOT NULL ,
    NR_NUM_PECAS  INTEGER NOT NULL ,
    ID_USUARIO    INTEGER ,
    ID_CARTA      INTEGER NOT NULL,
    ID_CONTINENTE INTEGER NOT NULL
  ) ;
ALTER TABLE TERRITORIO ADD CONSTRAINT TERRITORIO_PK PRIMARY KEY ( ID_TERRITORIO
) ;

CREATE TABLE USUARIO
  (
    ID_USUARIO  INTEGER NOT NULL ,
    NM_NOME     VARCHAR2 (150 CHAR) NOT NULL ,
    NM_COR      VARCHAR2 (20 CHAR) ,
    ID_OBJETIVO INTEGER ,
    BO_HUMANO   NUMBER(3),
    ID_JOGO     INTEGER NOT NULL
  ) ;
ALTER TABLE USUARIO ADD CONSTRAINT USUARIO_PK PRIMARY KEY ( ID_USUARIO ) ;

CREATE TABLE VIZINHO
  (
    ID_TERRITORIO         INTEGER NOT NULL ,
    ID_TERRITORIO_VIZINHO INTEGER NOT NULL
  ) ;
ALTER TABLE VIZINHO ADD CONSTRAINT VIZINHO_PK PRIMARY KEY ( ID_TERRITORIO,
ID_TERRITORIO_VIZINHO ) ;


ALTER TABLE CARTA ADD CONSTRAINT CARTA_USUARIO_FK FOREIGN KEY ( ID_USUARIO )
REFERENCES USUARIO ( ID_USUARIO ) ;

ALTER TABLE TERRITORIO ADD CONSTRAINT TERRITORIO_CARTA_FK FOREIGN KEY ( ID_CARTA ) REFERENCES CARTA ( ID_CARTA ) ;

ALTER TABLE TERRITORIO ADD CONSTRAINT TERRITORIO_CONTINENTE_FK FOREIGN KEY (
ID_CONTINENTE ) REFERENCES CONTINENTE ( ID_CONTINENTE ) ;

ALTER TABLE TERRITORIO ADD CONSTRAINT TERRITORIO_USUARIO_FK FOREIGN KEY (
ID_USUARIO ) REFERENCES USUARIO ( ID_USUARIO ) ;

ALTER TABLE VIZINHO ADD CONSTRAINT FK_ASS_7 FOREIGN KEY ( ID_TERRITORIO )
REFERENCES TERRITORIO ( ID_TERRITORIO ) ;

ALTER TABLE VIZINHO ADD CONSTRAINT FK_ASS_8 FOREIGN KEY (
ID_TERRITORIO_VIZINHO ) REFERENCES TERRITORIO ( ID_TERRITORIO ) ;

ALTER TABLE USUARIO ADD CONSTRAINT USUARIO_OBJETIVO_FK FOREIGN KEY (
ID_OBJETIVO ) REFERENCES OBJETIVO ( ID_OBJETIVO ) ;

ALTER TABLE USUARIO ADD CONSTRAINT USUARIO_JOGO_FK FOREIGN KEY ( ID_JOGO
) REFERENCES JOGO ( ID_JOGO ) ;


CREATE SEQUENCE JOGO_SEQ_ID
  START WITH 1
  MAXVALUE 9999999999999999999999999
  MINVALUE 1
  NOCYCLE
  NOCACHE
  NOORDER;
  
CREATE SEQUENCE USUARIO_SEQ_ID
  START WITH 1
  MAXVALUE 9999999999999999999999999
  MINVALUE 1
  NOCYCLE
  NOCACHE
  NOORDER;


insert into OBJETIVO (ID_OBJETIVO, NM_DESCRICAO) values (1,'Conquistar na totalidade a EUROPA, a OCEANIA e mais um terceiro');
insert into OBJETIVO (ID_OBJETIVO, NM_DESCRICAO) values (2,'Conquistar na totalidade a ASIA e a AMERICA DO SUL.');
insert into OBJETIVO (ID_OBJETIVO, NM_DESCRICAO) values (3,'Conquistar na totalidade a EUROPA, a AMERICA DO SUL e mais um terceiro.');
insert into OBJETIVO (ID_OBJETIVO, NM_DESCRICAO) values (4,'Conquistar 18 TERRITORIOS e ocupar cada um deles com pelo menos dois exercitos.');
insert into OBJETIVO (ID_OBJETIVO, NM_DESCRICAO) values (5,'Conquistar na totalidade a ASIA e a AFRICA.');
insert into OBJETIVO (ID_OBJETIVO, NM_DESCRICAO) values (6,'Conquistar na totalidade a AMERICA DO NORTE e a AFRICA.');
insert into OBJETIVO (ID_OBJETIVO, NM_DESCRICAO) values (7,'Conquistar 24 TERRITORIOS à sua escolha.');
insert into OBJETIVO (ID_OBJETIVO, NM_DESCRICAO) values (8,'Conquistar na totalidade a AMERICA DO NORTE e a OCEANIA.');
insert into OBJETIVO (ID_OBJETIVO, NM_DESCRICAO) values (9,'Destruir totalmente OS EXERCITOS AZUIS. Se não houver Jogador de Cor AZUL, conquistar 24 territórios de sua escolha.');
insert into OBJETIVO (ID_OBJETIVO, NM_DESCRICAO) values (10,'Destruir totalmente OS EXERCITOS AMARELOS. Se não houver Jogador de Cor AMARELA, conquistar 24 territórios de sua escolha.');
insert into OBJETIVO (ID_OBJETIVO, NM_DESCRICAO) values (11,'Destruir totalmente OS EXERCITOS VERMELHOS. Se não houver Jogador de Cor VERMELHA, conquistar 24 territórios de sua escolha.');
insert into OBJETIVO (ID_OBJETIVO, NM_DESCRICAO) values (12,'Destruir totalmente OS EXERCITOS ROXOS. Se não houver Jogador de Cor ROXA, conquistar 24 territórios de sua escolha.');
insert into OBJETIVO (ID_OBJETIVO, NM_DESCRICAO) values (13,'Destruir totalmente OS EXERCITOS ROSAS. Se não houver Jogador de Cor ROSA, conquistar 24 territórios de sua escolha.');
insert into OBJETIVO (ID_OBJETIVO, NM_DESCRICAO) values (14,'Destruir totalmente OS EXERCITOS VERDES. Se não houver Jogador de Cor VERDE, conquistar 24 territórios de sua escolha.');

insert into CONTINENTE (ID_CONTINENTE, NM_NOME) values (1, 'America do Sul');
insert into CONTINENTE (ID_CONTINENTE, NM_NOME) values (2, 'America do Norte');
insert into CONTINENTE (ID_CONTINENTE, NM_NOME) values (3, 'Europa');
insert into CONTINENTE (ID_CONTINENTE, NM_NOME) values (4, 'Africa');
insert into CONTINENTE (ID_CONTINENTE, NM_NOME) values (5, 'Asia');
insert into CONTINENTE (ID_CONTINENTE, NM_NOME) values (6, 'Oceania');

insert into CARTA(ID_CARTA, NM_SIMBOLO, BO_CORINGA) values(1, 'Quadrado', 0);
insert into CARTA(ID_CARTA, NM_SIMBOLO, BO_CORINGA) values(2, 'Bola', 0);
insert into CARTA(ID_CARTA, NM_SIMBOLO, BO_CORINGA) values(3, 'Triangulo', 0);
insert into CARTA(ID_CARTA, NM_SIMBOLO, BO_CORINGA) values(4, 'Quadrado', 0);
insert into CARTA(ID_CARTA, NM_SIMBOLO, BO_CORINGA) values(5, 'Bola', 0);
insert into CARTA(ID_CARTA, NM_SIMBOLO, BO_CORINGA) values(6, 'Triangulo', 0);
insert into CARTA(ID_CARTA, NM_SIMBOLO, BO_CORINGA) values(7, 'Quadrado', 0);
insert into CARTA(ID_CARTA, NM_SIMBOLO, BO_CORINGA) values(8, 'Bola', 0);
insert into CARTA(ID_CARTA, NM_SIMBOLO, BO_CORINGA) values(9, 'Triangulo', 0);
insert into CARTA(ID_CARTA, NM_SIMBOLO, BO_CORINGA) values(10, 'Quadrado', 0);
insert into CARTA(ID_CARTA, NM_SIMBOLO, BO_CORINGA) values(11, 'Bola', 0);
insert into CARTA(ID_CARTA, NM_SIMBOLO, BO_CORINGA) values(12, 'Triangulo', 0);
insert into CARTA(ID_CARTA, NM_SIMBOLO, BO_CORINGA) values(13, 'Quadrado', 0);
insert into CARTA(ID_CARTA, NM_SIMBOLO, BO_CORINGA) values(14, 'Bola', 0);
insert into CARTA(ID_CARTA, NM_SIMBOLO, BO_CORINGA) values(15, 'Triangulo', 0);
insert into CARTA(ID_CARTA, NM_SIMBOLO, BO_CORINGA) values(16, 'Quadrado', 0);
insert into CARTA(ID_CARTA, NM_SIMBOLO, BO_CORINGA) values(17, 'Bola', 0);
insert into CARTA(ID_CARTA, NM_SIMBOLO, BO_CORINGA) values(18, 'Triangulo', 0);
insert into CARTA(ID_CARTA, NM_SIMBOLO, BO_CORINGA) values(19, 'Quadrado', 0);
insert into CARTA(ID_CARTA, NM_SIMBOLO, BO_CORINGA) values(20, 'Bola', 0);
insert into CARTA(ID_CARTA, NM_SIMBOLO, BO_CORINGA) values(21, 'Triangulo', 0);
insert into CARTA(ID_CARTA, NM_SIMBOLO, BO_CORINGA) values(22, 'Quadrado', 0);
insert into CARTA(ID_CARTA, NM_SIMBOLO, BO_CORINGA) values(23, 'Bola', 0);
insert into CARTA(ID_CARTA, NM_SIMBOLO, BO_CORINGA) values(24, 'Triangulo', 0);
insert into CARTA(ID_CARTA, NM_SIMBOLO, BO_CORINGA) values(25, 'Quadrado', 0);
insert into CARTA(ID_CARTA, NM_SIMBOLO, BO_CORINGA) values(26, 'Bola', 0);
insert into CARTA(ID_CARTA, NM_SIMBOLO, BO_CORINGA) values(27, 'Triangulo', 0);
insert into CARTA(ID_CARTA, NM_SIMBOLO, BO_CORINGA) values(28, 'Quadrado', 0);
insert into CARTA(ID_CARTA, NM_SIMBOLO, BO_CORINGA) values(29, 'Bola', 0);
insert into CARTA(ID_CARTA, NM_SIMBOLO, BO_CORINGA) values(30, 'Triangulo', 0);
insert into CARTA(ID_CARTA, NM_SIMBOLO, BO_CORINGA) values(31, 'Quadrado', 0);
insert into CARTA(ID_CARTA, NM_SIMBOLO, BO_CORINGA) values(32, 'Bola', 0);
insert into CARTA(ID_CARTA, NM_SIMBOLO, BO_CORINGA) values(33, 'Triangulo', 0);
insert into CARTA(ID_CARTA, NM_SIMBOLO, BO_CORINGA) values(34, 'Quadrado', 0);
insert into CARTA(ID_CARTA, NM_SIMBOLO, BO_CORINGA) values(35, 'Bola', 0);
insert into CARTA(ID_CARTA, NM_SIMBOLO, BO_CORINGA) values(36, 'Triangulo', 0);
insert into CARTA(ID_CARTA, NM_SIMBOLO, BO_CORINGA) values(37, 'Quadrado', 0);
insert into CARTA(ID_CARTA, NM_SIMBOLO, BO_CORINGA) values(38, 'Bola', 0);
insert into CARTA(ID_CARTA, NM_SIMBOLO, BO_CORINGA) values(39, 'Triangulo', 0);
insert into CARTA(ID_CARTA, NM_SIMBOLO, BO_CORINGA) values(40, 'Quadrado', 0);
insert into CARTA(ID_CARTA, NM_SIMBOLO, BO_CORINGA) values(41, 'Bola', 0);
insert into CARTA(ID_CARTA, NM_SIMBOLO, BO_CORINGA) values(42, 'Triangulo', 0);
insert into CARTA(ID_CARTA, NM_SIMBOLO, BO_CORINGA) values(43, 'Quadrado-Bola-Triangulo', 1);
insert into CARTA(ID_CARTA, NM_SIMBOLO, BO_CORINGA) values(44, 'Quadrado-Bola-Triangulo', 1);

insert into TERRITORIO(ID_TERRITORIO, ID_CARTA, NM_NOME, NR_NUM_PECAS, ID_CONTINENTE) values(1, 1, 'Brasil', 0, 1);
insert into TERRITORIO(ID_TERRITORIO, ID_CARTA, NM_NOME, NR_NUM_PECAS, ID_CONTINENTE) values(2, 2, 'Argentina-Uruguai', 0, 1);
insert into TERRITORIO(ID_TERRITORIO, ID_CARTA, NM_NOME, NR_NUM_PECAS, ID_CONTINENTE) values(3, 3, 'Chile-Bolivia-Peru', 0, 1);
insert into TERRITORIO(ID_TERRITORIO, ID_CARTA, NM_NOME, NR_NUM_PECAS, ID_CONTINENTE) values(4, 4, 'Colombia-Venezuela', 0, 1);
insert into TERRITORIO(ID_TERRITORIO, ID_CARTA, NM_NOME, NR_NUM_PECAS, ID_CONTINENTE) values(5, 5, 'Mexico', 0, 2);
insert into TERRITORIO(ID_TERRITORIO, ID_CARTA, NM_NOME, NR_NUM_PECAS, ID_CONTINENTE) values(6, 6, 'Nova-York', 0, 2);
insert into TERRITORIO(ID_TERRITORIO, ID_CARTA, NM_NOME, NR_NUM_PECAS, ID_CONTINENTE) values(7, 7, 'California', 0, 2);
insert into TERRITORIO(ID_TERRITORIO, ID_CARTA, NM_NOME, NR_NUM_PECAS, ID_CONTINENTE) values(8, 8, 'Ottawa', 0, 2);
insert into TERRITORIO(ID_TERRITORIO, ID_CARTA, NM_NOME, NR_NUM_PECAS, ID_CONTINENTE) values(9, 9, 'Vancouver', 0, 2);
insert into TERRITORIO(ID_TERRITORIO, ID_CARTA, NM_NOME, NR_NUM_PECAS, ID_CONTINENTE) values(10, 10, 'Labrador', 0, 2);
insert into TERRITORIO(ID_TERRITORIO, ID_CARTA, NM_NOME, NR_NUM_PECAS, ID_CONTINENTE) values(11, 11, 'Alaska', 0, 2);
insert into TERRITORIO(ID_TERRITORIO, ID_CARTA, NM_NOME, NR_NUM_PECAS, ID_CONTINENTE) values(12, 12, 'Mackenzie', 0, 2);
insert into TERRITORIO(ID_TERRITORIO, ID_CARTA, NM_NOME, NR_NUM_PECAS, ID_CONTINENTE) values(13, 13, 'Groelandia', 0, 2);
insert into TERRITORIO(ID_TERRITORIO, ID_CARTA, NM_NOME, NR_NUM_PECAS, ID_CONTINENTE) values(14, 14, 'Islandia', 0, 3);
insert into TERRITORIO(ID_TERRITORIO, ID_CARTA, NM_NOME, NR_NUM_PECAS, ID_CONTINENTE) values(15, 15, 'Inglaterra', 0, 3);
insert into TERRITORIO(ID_TERRITORIO, ID_CARTA, NM_NOME, NR_NUM_PECAS, ID_CONTINENTE) values(16, 16, 'Suecia', 0, 3);
insert into TERRITORIO(ID_TERRITORIO, ID_CARTA, NM_NOME, NR_NUM_PECAS, ID_CONTINENTE) values(17, 17, 'Moscou', 0, 3);
insert into TERRITORIO(ID_TERRITORIO, ID_CARTA, NM_NOME, NR_NUM_PECAS, ID_CONTINENTE) values(18, 18, 'Polonia-Iugoslavia', 0, 3);
insert into TERRITORIO(ID_TERRITORIO, ID_CARTA, NM_NOME, NR_NUM_PECAS, ID_CONTINENTE) values(19, 19, 'Portugal-Espanha-Franca', 0, 3);
insert into TERRITORIO(ID_TERRITORIO, ID_CARTA, NM_NOME, NR_NUM_PECAS, ID_CONTINENTE) values(20, 20, 'Alemanha', 0, 3);
insert into TERRITORIO(ID_TERRITORIO, ID_CARTA, NM_NOME, NR_NUM_PECAS, ID_CONTINENTE) values(21, 21, 'Argelia-Nigeria', 0, 4);
insert into TERRITORIO(ID_TERRITORIO, ID_CARTA, NM_NOME, NR_NUM_PECAS, ID_CONTINENTE) values(22, 22, 'Egito', 0, 4);
insert into TERRITORIO(ID_TERRITORIO, ID_CARTA, NM_NOME, NR_NUM_PECAS, ID_CONTINENTE) values(23, 23, 'Sudao', 0, 4);
insert into TERRITORIO(ID_TERRITORIO, ID_CARTA, NM_NOME, NR_NUM_PECAS, ID_CONTINENTE) values(24, 24, 'Congo', 0, 4);
insert into TERRITORIO(ID_TERRITORIO, ID_CARTA, NM_NOME, NR_NUM_PECAS, ID_CONTINENTE) values(25, 25, 'Africa-do-Sul', 0, 4);
insert into TERRITORIO(ID_TERRITORIO, ID_CARTA, NM_NOME, NR_NUM_PECAS, ID_CONTINENTE) values(26, 26, 'Madagascar', 0, 4);
insert into TERRITORIO(ID_TERRITORIO, ID_CARTA, NM_NOME, NR_NUM_PECAS, ID_CONTINENTE) values(27, 27, 'Oriente-Medio', 0, 5);
insert into TERRITORIO(ID_TERRITORIO, ID_CARTA, NM_NOME, NR_NUM_PECAS, ID_CONTINENTE) values(28, 28, 'Aral', 0, 5);
insert into TERRITORIO(ID_TERRITORIO, ID_CARTA, NM_NOME, NR_NUM_PECAS, ID_CONTINENTE) values(29, 29, 'Omsk', 0, 5);
insert into TERRITORIO(ID_TERRITORIO, ID_CARTA, NM_NOME, NR_NUM_PECAS, ID_CONTINENTE) values(30, 30, 'Dudinka', 0, 5);
insert into TERRITORIO(ID_TERRITORIO, ID_CARTA, NM_NOME, NR_NUM_PECAS, ID_CONTINENTE) values(31, 31, 'Siberia', 0, 5);
insert into TERRITORIO(ID_TERRITORIO, ID_CARTA, NM_NOME, NR_NUM_PECAS, ID_CONTINENTE) values(32, 32, 'Tchita', 0, 5);
insert into TERRITORIO(ID_TERRITORIO, ID_CARTA, NM_NOME, NR_NUM_PECAS, ID_CONTINENTE) values(33, 33, 'Mongolia', 0, 5);
insert into TERRITORIO(ID_TERRITORIO, ID_CARTA, NM_NOME, NR_NUM_PECAS, ID_CONTINENTE) values(34, 34, 'China', 0, 5);
insert into TERRITORIO(ID_TERRITORIO, ID_CARTA, NM_NOME, NR_NUM_PECAS, ID_CONTINENTE) values(35, 35, 'India', 0, 5);
insert into TERRITORIO(ID_TERRITORIO, ID_CARTA, NM_NOME, NR_NUM_PECAS, ID_CONTINENTE) values(36, 36, 'Vietna', 0, 5);
insert into TERRITORIO(ID_TERRITORIO, ID_CARTA, NM_NOME, NR_NUM_PECAS, ID_CONTINENTE) values(37, 37, 'Japao', 0, 5);
insert into TERRITORIO(ID_TERRITORIO, ID_CARTA, NM_NOME, NR_NUM_PECAS, ID_CONTINENTE) values(38, 38, 'Vladivostok', 0, 5);
insert into TERRITORIO(ID_TERRITORIO, ID_CARTA, NM_NOME, NR_NUM_PECAS, ID_CONTINENTE) values(39, 39, 'Sumatra', 0, 6);
insert into TERRITORIO(ID_TERRITORIO, ID_CARTA, NM_NOME, NR_NUM_PECAS, ID_CONTINENTE) values(40, 40, 'Borneo', 0, 6);
insert into TERRITORIO(ID_TERRITORIO, ID_CARTA, NM_NOME, NR_NUM_PECAS, ID_CONTINENTE) values(41, 41, 'Nova-Guine', 0, 6);
insert into TERRITORIO(ID_TERRITORIO, ID_CARTA, NM_NOME, NR_NUM_PECAS, ID_CONTINENTE) values(42, 42, 'Australia', 0, 6);

--America do Sul
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (1, 2);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (1, 3);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (1, 4);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (1, 21);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (2, 1);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (2, 3);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (3, 1);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (3, 2);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (3, 4);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (4, 1);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (4, 3);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (4, 5);
--America do Norte
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (5, 4);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (5, 6);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (5, 7);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (6, 5);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (6, 7);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (6, 8);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (6, 10);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (7, 5);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (7, 6);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (7, 8);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (7, 9);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (8, 6);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (8, 7);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (8, 9);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (8, 10);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (8, 12);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (9, 7);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (9, 8);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (9, 11);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (9, 12);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (10, 6);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (10, 8);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (10, 13);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (11, 9);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (11, 12);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (11, 38);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (12, 8);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (12, 9);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (12, 11);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (12, 13);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (13, 10);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (13, 12);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (13, 14);
--Europa
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (14, 13);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (14, 15);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (14, 16);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (15, 14);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (15, 16);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (15, 19);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (15, 20);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (16, 14);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (16, 15);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (16, 17);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (17, 16);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (17, 19);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (17, 27);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (17, 28);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (17, 29);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (18, 17);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (18, 19);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (18, 20);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (18, 22);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (18, 27);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (19, 15);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (19, 18);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (19, 20);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (19, 21);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (19, 22);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (20, 15);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (20, 18);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (20, 19);
--Africa
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (21, 1);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (21, 19);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (21, 22);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (21, 23);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (21, 24);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (22, 18);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (22, 19);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (22, 21);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (22, 23);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (22, 27);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (23, 21);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (23, 22);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (23, 24);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (23, 25);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (23, 26);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (24, 21);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (24, 23);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (24, 25);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (25, 23);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (25, 24);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (25, 26);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (26, 23);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (26, 25);
--Asia
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (27, 17);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (27, 18);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (27, 22);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (27, 28);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (27, 35);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (28, 17);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (28, 27);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (28, 29);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (28, 34);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (28, 35);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (29, 17);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (29, 28);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (29, 30);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (29, 33);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (29, 34);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (30, 29);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (30, 31);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (30, 32);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (30, 33);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (31, 30);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (31, 32);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (31, 38);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (32, 30);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (32, 31);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (32, 33);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (32, 34);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (32, 38);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (33, 29);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (33, 30);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (33, 32);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (33, 34);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (34, 28);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (34, 29);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (34, 32);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (34, 33);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (34, 35);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (34, 36);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (34, 37);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (34, 38);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (35, 27);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (35, 28);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (35, 34);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (35, 36);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (35, 39);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (36, 34);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (36, 35);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (36, 40);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (37, 34);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (37, 38);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (38, 11);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (38, 31);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (38, 32);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (38, 34);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (38, 37);
--Oceania
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (39, 35);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (39, 42);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (40, 36);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (40, 41);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (40, 42);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (41, 40);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (41, 42);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (42, 39);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (42, 40);
insert into VIZINHO(ID_TERRITORIO, ID_TERRITORIO_VIZINHO) values (42, 41);
