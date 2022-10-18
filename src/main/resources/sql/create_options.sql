drop table options;

CREATE TABLE options (
	option_id	VARCHAR(255)	NOT NULL,
	menu_id	VARCHAR(255)	NOT NULL,
	name	VARCHAR(255)	NULL,
	desc	VARCHAR(255)	NULL,
	state	INT	NULL,
	create_date	DATETIME	NULL,
	modify_date	DATETIME	NULL
);

ALTER TABLE options ADD CONSTRAVARCHAR(255) PK_OPTIONS PRIMARY KEY (
	option_id,
	menu_id
);

insert into options(option_id, menu_id, name)
VALUES('1001', '2001', 'test name 1');
insert into options(option_id, menu_id, name)
VALUES('1002', '2001', 'test name 2');
insert into options(option_id, menu_id, name)
VALUES('1003', '2002', 'test name 3');
commit;