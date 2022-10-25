DROP TABLE options;

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