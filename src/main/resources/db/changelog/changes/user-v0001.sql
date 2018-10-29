CREATE TABLE USER_MASTER
(
    ID serial primary key,
    BUS_NAME character varying(50),
	DEF_LOCN_NBR integer not null default 0,
    USER_NAME character varying(50),
    AUTH_TYPE character varying(50),
    AUTH_TOKEN text,
    FIRST_NAME character varying(50),
    LAST_NAME character varying(50),
    MIDDLE_NAME character varying(50),
    ROLE_NAME character varying(50),
    ADDR_1 character varying(50),
    ADDR_2 character varying(50),
    ADDR_3 character varying(50),
    CITY character varying(50),
    STATE character varying(50),
    COUNTRY character varying(50),
    ZIPCODE character varying(50),
    STATUS character varying(50),
    LOCALE character varying(50),
	SOURCE character varying(50),
	TRANSACTION_NAME character varying(50),
	REF_FIELD_1  character varying(50),
	REF_FIELD_2  character varying(50),
	HOST_NAME  character varying(50),
    CREATED_DTTM  timestamp not null default NOW(),
    UPDATED_DTTM  timestamp not null default NOW(),
    CREATED_BY character varying(25),
    UPDATED_BY character varying(25),
    VERSION INTEGER
);


