DROP TABLE ORDERS;

CREATE TABLE ORDERS (
	order_id	VARCHAR(255)	NOT NULL,
	user_id	VARCHAR(255)	NOT NULL,
	menu_snapshot_id	VARCHAR(255)	null,
	total_price	INT	null,
	request	VARCHAR(255)	null,
	state	VARCHAR(255)	null	COMMENT '0: 결제완료, 1: 결제중',
	payment	INT	null	COMMENT '0: 현금결제, 1: 카드결제, 2: 페이결제',
	rider_id	VARCHAR(255)	null,
	delivery_tip	INT	null,
	create_date	DATETIME	null	COMMENT '주문 시간'
);

ALTER TABLE ORDERS ADD CONSTRAINT PK_ORDER PRIMARY KEY (
	order_id
);

INSERT INTO ORDERS(order_id, user_id, state)
VALUES('8164faf0-ed43-4d7a-9b1c-e1986df4745e', '222', '0')
;