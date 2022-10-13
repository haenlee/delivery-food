drop table order_menu;

CREATE TABLE ORDER_MENU (
	menu_id	VARCHAR(255)	NOT NULL,
	option_id	VARCHAR(255)	NOT NULL,
	sub_option_id	VARCHAR(255)	NOT NULL,
	count	INT	NULL	DEFAULT 1,
	price	INT	NULL
);

ALTER TABLE ORDER_MENU ADD CONSTRAINT PK_ORDER_MENU PRIMARY KEY (
	menu_id
);

insert into sub_options(option_id, menu_id, sub_option_id)
VALUES(1004, 2004, 3004)
;