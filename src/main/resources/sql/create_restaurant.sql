DROP TABLE RESTAURANT;

CREATE TABLE RESTAURANT (
	restaurant_id	VARCHAR(255)	NOT NULL,
	user_id	VARCHAR(255)	NOT NULL,
	name	VARCHAR(255)	null,
	business_id	INT	null,
	category	INT	null,
	address	VARCHAR(255)	null,
	phone	VARCHAR(20)	null,
	hours	VARCHAR(20)	null,
	distance	INT	null,
	state	INT	null	COMMENT '0: 운영, 1: 폐업',
	createDate	DATETIME	null,
	modifyDate	DATETIME	NULL
);

ALTER TABLE RESTAURANT ADD CONSTRAINT PK_RESTAURANT PRIMARY KEY (
	restaurant_id,
	user_id
);