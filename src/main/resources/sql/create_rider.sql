DROP TABLE RIDER;

create table RIDER
(
    USER_ID    VARCHAR(255) not null,
    COMMISSION INT,
    STATUS     VARCHAR(255),
    REG_DT     TIMESTAMP,
    UDT_DT     TIMESTAMP,
    primary key (USER_ID)
);