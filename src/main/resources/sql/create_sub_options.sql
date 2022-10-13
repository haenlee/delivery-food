drop table sub_options;

CREATE TABLE sub_options (
	menu_id	VARCHAR(255)	NOT NULL,
	option_id	VARCHAR(255)	NOT NULL,
	sub_option_id	VARCHAR(255)	NOT NULL,
	name	VARCHAR(255)	NULL,
	price	VARCHAR(255)	NULL,
	create_date	DATETIME	NULL,
	modify_date	DATETIME	NULL
);

ALTER TABLE sub_options ADD CONSTRAVARCHAR(255) PK_SUB_OPTIONS PRIMARY KEY (
	menu_id,
	option_id
);

insert into sub_options(option_id, menu_id, sub_option_id)
VALUES('1002', '2002', '3002')
;