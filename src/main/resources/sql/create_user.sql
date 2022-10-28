DROP TABLE USER;

create table USER
(
    USER_ID    VARCHAR(255) not null,
    ADDRESS    VARCHAR(255),
    NICKNAME   VARCHAR(255),
    GRADE      VARCHAR(255),
    IMAGE_PATH VARCHAR(255),
    REG_DT     TIMESTAMP,
    UDT_DT     TIMESTAMP,
    primary key (USER_ID)
);