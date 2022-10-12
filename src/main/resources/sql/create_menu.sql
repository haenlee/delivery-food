DROP TABLE MENU;

CREATE TABLE MENU (
	menu_id	VARCHAR(255)	NOT NULL,
	restaurant_id	VARCHAR(255)	NOT NULL,
	name	VARCHAR(255)	null,
	price	INT	null,
	desc	VARCHAR(255)	NULL,
	image_path	VARCHAR(255)	NULL,
	state	INT	null	COMMENT '0: 주문가능, 1: 품절',
	create_date	DATETIME	null,
	modify_date	DATETIME	NULL
);

ALTER TABLE MENU ADD CONSTRAINT PK_MENU PRIMARY KEY (
	menu_id,
	restaurant_id
);