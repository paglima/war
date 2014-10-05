CREATE USER TrabalhoES IDENTIFIED BY TrabalhoES;
GRANT CONNECT,  RESOURCE, CREATE table, CREATE synonym, CREATE sequence TO TrabalhoES;

CREATE TABLE OBJETIVO 
(
  ID_OBJETIVO          number, 
  NM_DESCRICAO          varchar2(500)
);

ALTER TABLE OBJETIVO ADD CONSTRAINT PK_OBJETIVO PRIMARY KEY (ID_OBJETIVO);

CREATE SEQUENCE OBJETIVO_SEQ_ID
  START WITH 1
  MAXVALUE 9999999999999999999999999
  MINVALUE 1
  NOCYCLE
  NOCACHE
  NOORDER;

CREATE TABLE USUARIO 
(
  ID_USUARIO          number,
  NM_NOME_USUARIO     varchar(200),
  ID_OBJETIVO          number
);

ALTER TABLE USUARIO
ADD CONSTRAINT FK_OBJETIVO 
FOREIGN KEY (ID_OBJETIVO) REFERENCES OBJETIVO (ID_OBJETIVO);

   
CREATE SEQUENCE USUARIO_SEQ_ID
  START WITH 1
  MAXVALUE 9999999999999999999999999
  MINVALUE 1
  NOCYCLE
  NOCACHE
  NOORDER;

  
CREATE TABLE TERRITORIO
(
  ID_TERRITORIO          number, 
  NM_TERRITORIO          varchar2(500),
  ID_USUARIO             number not null 
);


ALTER TABLE TERRITORIO ADD CONSTRAINT PK_TERRITORIO PRIMARY KEY (ID_TERRITORIO);
ALTER TABLE TERRITORIO ADD CONSTRAINT FK_USUARIO FOREIGN KEY (ID_USUARIO) REFERENCES USUARIO (ID_USUARIO) ON DELETE SET NULL;



CREATE SEQUENCE TERRITORIO_SEQ_ID
  START WITH 1
  MAXVALUE 9999999999999999999999999
  MINVALUE 1
  NOCYCLE
  NOCACHE
  NOORDER;


insert into TERRITORIO(id_territorio, nm_territorio) values(TERRITORIO_SEQ_ID.nextval,'Brasil');
insert into TERRITORIO(id_territorio, nm_territorio) values(TERRITORIO_SEQ_ID.nextval,'Argentina');
insert into TERRITORIO(id_territorio, nm_territorio) values(TERRITORIO_SEQ_ID.nextval,'Uruguai');
insert into TERRITORIO(id_territorio, nm_territorio) values(TERRITORIO_SEQ_ID.nextval,'Chile');
insert into TERRITORIO(id_territorio, nm_territorio) values(TERRITORIO_SEQ_ID.nextval,'Bolivia');
insert into TERRITORIO(id_territorio, nm_territorio) values(TERRITORIO_SEQ_ID.nextval,'Peru');
insert into TERRITORIO(id_territorio, nm_territorio) values(TERRITORIO_SEQ_ID.nextval,'Colombia');
insert into TERRITORIO(id_territorio, nm_territorio) values(TERRITORIO_SEQ_ID.nextval,'Venezuela');
insert into TERRITORIO(id_territorio, nm_territorio) values(TERRITORIO_SEQ_ID.nextval,'Argentina');
insert into TERRITORIO(id_territorio, nm_territorio) values(TERRITORIO_SEQ_ID.nextval,'México');
insert into TERRITORIO(id_territorio, nm_territorio) values(TERRITORIO_SEQ_ID.nextval,'Nova York');
insert into TERRITORIO(id_territorio, nm_territorio) values(TERRITORIO_SEQ_ID.nextval,'Califórnia');
insert into TERRITORIO(id_territorio, nm_territorio) values(TERRITORIO_SEQ_ID.nextval,'Vancouver');
insert into TERRITORIO(id_territorio, nm_territorio) values(TERRITORIO_SEQ_ID.nextval,'Ottawa');
insert into TERRITORIO(id_territorio, nm_territorio) values(TERRITORIO_SEQ_ID.nextval,'Labrador');
insert into TERRITORIO(id_territorio, nm_territorio) values(TERRITORIO_SEQ_ID.nextval,'Alaska');
insert into TERRITORIO(id_territorio, nm_territorio) values(TERRITORIO_SEQ_ID.nextval,'Mackenzie');
insert into TERRITORIO(id_territorio, nm_territorio) values(TERRITORIO_SEQ_ID.nextval,'Groelândia');

insert into TERRITORIO(id_territorio, nm_territorio) values(TERRITORIO_SEQ_ID.nextval,'Islândia');
insert into TERRITORIO(id_territorio, nm_territorio) values(TERRITORIO_SEQ_ID.nextval,'Inglaterra');
insert into TERRITORIO(id_territorio, nm_territorio) values(TERRITORIO_SEQ_ID.nextval,'Portugal');
insert into TERRITORIO(id_territorio, nm_territorio) values(TERRITORIO_SEQ_ID.nextval,'Espanha');
insert into TERRITORIO(id_territorio, nm_territorio) values(TERRITORIO_SEQ_ID.nextval,'França');
insert into TERRITORIO(id_territorio, nm_territorio) values(TERRITORIO_SEQ_ID.nextval,'Alemanha');
insert into TERRITORIO(id_territorio, nm_territorio) values(TERRITORIO_SEQ_ID.nextval,'Polônia');
insert into TERRITORIO(id_territorio, nm_territorio) values(TERRITORIO_SEQ_ID.nextval,'Iugoslávia');
insert into TERRITORIO(id_territorio, nm_territorio) values(TERRITORIO_SEQ_ID.nextval,'Suécia');
insert into TERRITORIO(id_territorio, nm_territorio) values(TERRITORIO_SEQ_ID.nextval,'Moscou');

insert into TERRITORIO(id_territorio, nm_territorio) values(TERRITORIO_SEQ_ID.nextval,'Oriente Médio');
insert into TERRITORIO(id_territorio, nm_territorio) values(TERRITORIO_SEQ_ID.nextval,'Aral');
insert into TERRITORIO(id_territorio, nm_territorio) values(TERRITORIO_SEQ_ID.nextval,'Omsk');
insert into TERRITORIO(id_territorio, nm_territorio) values(TERRITORIO_SEQ_ID.nextval,'Dudinka');
insert into TERRITORIO(id_territorio, nm_territorio) values(TERRITORIO_SEQ_ID.nextval,'Sibéria');
insert into TERRITORIO(id_territorio, nm_territorio) values(TERRITORIO_SEQ_ID.nextval,'Tchita');
insert into TERRITORIO(id_territorio, nm_territorio) values(TERRITORIO_SEQ_ID.nextval,'Mongólia');
insert into TERRITORIO(id_territorio, nm_territorio) values(TERRITORIO_SEQ_ID.nextval,'China');
insert into TERRITORIO(id_territorio, nm_territorio) values(TERRITORIO_SEQ_ID.nextval,'Índia');
insert into TERRITORIO(id_territorio, nm_territorio) values(TERRITORIO_SEQ_ID.nextval,'Vietnã');
insert into TERRITORIO(id_territorio, nm_territorio) values(TERRITORIO_SEQ_ID.nextval,'Japão');

insert into TERRITORIO(id_territorio, nm_territorio) values(TERRITORIO_SEQ_ID.nextval,'Sumatra');
insert into TERRITORIO(id_territorio, nm_territorio) values(TERRITORIO_SEQ_ID.nextval,'Borneo');
insert into TERRITORIO(id_territorio, nm_territorio) values(TERRITORIO_SEQ_ID.nextval,'Nova Guiné');
insert into TERRITORIO(id_territorio, nm_territorio) values(TERRITORIO_SEQ_ID.nextval,'Austrália');

insert into TERRITORIO(id_territorio, nm_territorio) values(TERRITORIO_SEQ_ID.nextval,'Argélia');
insert into TERRITORIO(id_territorio, nm_territorio) values(TERRITORIO_SEQ_ID.nextval,'Nigéria');
insert into TERRITORIO(id_territorio, nm_territorio) values(TERRITORIO_SEQ_ID.nextval,'Egito');
insert into TERRITORIO(id_territorio, nm_territorio) values(TERRITORIO_SEQ_ID.nextval,'Sudão');
insert into TERRITORIO(id_territorio, nm_territorio) values(TERRITORIO_SEQ_ID.nextval,'Congo');
insert into TERRITORIO(id_territorio, nm_territorio) values(TERRITORIO_SEQ_ID.nextval,'África do Sul');
insert into TERRITORIO(id_territorio, nm_territorio) values(TERRITORIO_SEQ_ID.nextval,'Madasgascar');