CREATE TABLE t_coin (
	type varchar(50) NOT NULL COMMENT '幣別',
	name varchar(50) NOT NULL COMMENT '中文名稱',
	CONSTRAINT t_coin_pk PRIMARY KEY (type)
);
