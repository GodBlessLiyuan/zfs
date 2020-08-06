DROP TABLE IF EXISTS t_phone_model;
DROP TABLE IF EXISTS t_phone_type;

CREATE TABLE t_phone_model
(
	model_id bigint NOT NULL AUTO_INCREMENT,
	type_id int NOT NULL,
	name char(128),
	picture char(255),
	-- 1 未删除  2删除
	dr tinyint COMMENT '1 未删除  2删除',
	PRIMARY KEY (model_id),
	UNIQUE (model_id)
);

CREATE TABLE t_phone_type
(
	type_id int NOT NULL AUTO_INCREMENT,
	ename char(32),
	name char(32),
	picture char(255),
	-- 1 未删除  2删除
	dr tinyint COMMENT '1 未删除  2删除',
	PRIMARY KEY (type_id),
	UNIQUE (type_id),
	UNIQUE (ename),
	UNIQUE (name)
);

ALTER TABLE t_phone_model
	ADD FOREIGN KEY (type_id)
	REFERENCES t_phone_type (type_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;






INSERT INTO smartzfs.t_phone_type (ename, name, dr) VALUES ('HUAWEI', '华为', 1), ('OPPO', 'OPPO', 1), ('VIVO', 'VIVO', 1), ('Xiaomi', '小米', 1), ('SAMSUNG', '三星', 1), ('MEIZU', '魅族', 1), ('APPLE', 'iPhone', 1), ('OnePlus', '一加', 1);

INSERT INTO smartzfs.t_phone_model (type_id, name, dr) VALUES
(1, 'Mate 10', 1),
(1, 'Mate 10 Pro', 1),
(1, 'Mate 10 保时捷版', 1),
(1, 'Mate 20', 1),
(1, 'Mate 20 Pro', 1),
(1, 'Mate 20 X', 1),
(1, 'mate 30', 1),
(1, 'mate 30 pro', 1),
(1, 'Mate 30 Pro 5G', 1),
(1, 'Mate 9 保时捷版', 1),
(1, 'Mate20 RS保时捷设计', 1),
(1, 'Mate30 RS 保时捷设计', 1),
(1, 'nova 4', 1),
(1, 'nova 5 Pro', 1),
(1, 'nova6 5G', 1),
(1, 'P10', 1),
(1, 'P10 Plus', 1),
(1, 'P20', 1),
(1, 'P20 Pro', 1),
(1, 'P30', 1),
(1, 'P30 Pro', 1),
(1, '华为RS 保时捷版', 1),
(1, '畅享9 Plus', 1),
(1, '荣耀 V10', 1),
(1, '荣耀10 GT', 1),
(1, '荣耀10青春版', 1),
(1, '荣耀20', 1),
(1, '荣耀20 PRO', 1),
(1, '荣耀20S', 1),
(1, '荣耀8X', 1),
(1, '荣耀9', 1),
(1, '荣耀9X', 1),
(1, '荣耀9XPro', 1),
(1, '荣耀Magic', 1),
(1, '荣耀Magic2', 1),
(1, '荣耀Note10', 1),
(1, '荣耀Play', 1),
(1, '荣耀V20', 1),
(1, '荣耀V9', 1),
(1, '麦芒7', 1);

INSERT INTO smartzfs.t_phone_model (type_id, name, dr) VALUES
(2, 'A5', 1),
(2, 'A7', 1),
(2, 'A77', 1),
(2, 'A7x', 1),
(2, 'Find X', 1),
(2, 'Find X 兰博基尼版', 1),
(2, 'K1', 1),
(2, 'OPPO A11x', 1),
(2, 'OPPO Reno 10倍变焦', 1),
(2, 'OPPO Reno Ace', 1),
(2, 'OPPO Reno3', 1),
(2, 'R11', 1),
(2, 'R11 plus', 1),
(2, 'R11s', 1),
(2, 'R15x', 1),
(2, 'R17', 1),
(2, 'R17 Pro', 1),
(2, 'Reno', 1);

INSERT INTO smartzfs.t_phone_model (type_id, name, dr) VALUES
(3, 'iQOO', 1),
(3, 'iQOO Monster', 1),
(3, 'NEX 2', 1),
(3, 'NEX双屏版', 1),
(3, 'U1', 1),
(3, 'VIVO iQOO Pro', 1),
(3, 'VIVO NEX3', 1),
(3, 'X20', 1),
(3, 'X20 Plus', 1),
(3, 'X20A', 1),
(3, 'X21A', 1),
(3, 'X23', 1),
(3, 'X27', 1),
(3, 'X30 Pro', 1),
(3, 'X9', 1),
(3, 'X9 Plus', 1),
(3, 'X9i', 1),
(3, 'X9L', 1),
(3, 'X9s', 1),
(3, 'Xplay6', 1),
(3, 'Y66', 1),
(3, 'Y81s', 1),
(3, 'Z1', 1),
(3, 'Z3', 1);

INSERT INTO smartzfs.t_phone_model (type_id, name, dr) VALUES
(4, 'Redmi K20pro', 1),
(4, '小米5X', 1),
(4, '小米6', 1),
(4, '小米6X', 1),
(4, '小米8 SE', 1),
(4, '小米8屏幕指纹版', 1),
(4, '小米9', 1),
(4, '小米9 pro 5G', 1),
(4, '小米9 透明尊享版', 1),
(4, '小米CC', 1),
(4, '小米MIX 2S', 1),
(4, '小米MIX Alpha', 1),
(4, '小米MIX2', 1),
(4, '小米MIX3', 1),
(4, '红米4X', 1),
(4, '红米Note 4X', 1),
(4, '红米Note7', 1);

INSERT INTO smartzfs.t_phone_model (type_id, name, dr) VALUES
(5, 'Galaxy A8s', 1),
(5, 'Galaxy A9 Star', 1),
(5, 'Galaxy Note10', 1),
(5, 'Galaxy Note8', 1),
(5, 'Galaxy Note9', 1),
(5, 'Galaxy S10+', 1),
(5, 'Galaxy S9+', 1),
(5, 'W2019', 1),
(5, '三星Note 8', 1),
(5, '三星S10e', 1),
(5, '三星领世旗舰8', 1);

INSERT INTO smartzfs.t_phone_model (type_id, name, dr) VALUES
(6, '15', 1),
(6, '16s', 1),
(6, 'm6 note', 1),
(6, 'Note9', 1),
(6, 'PRO6 Plus', 1),
(6, 'PRO7', 1),
(6, 'PRO7 Plus', 1),
(6, '魅族 16th', 1),
(6, '魅族 16X', 1),
(6, '魅族 Note8', 1),
(6, '魅族16s Pro', 1),
(6, '魅族V8', 1),
(6, '魅族X8', 1);

INSERT INTO smartzfs.t_phone_model (type_id, name, dr) VALUES
(7, '6s Ρlus', 1),
(7, 'iPhone 11', 1),
(7, 'iPhone 11 Pro', 1),
(7, 'iPhone 11 Pro Max', 1),
(7, 'iPhone X', 1),
(7, 'iPhone XR', 1),
(7, 'iPhone XS', 1),
(7, 'iPhone XS Max', 1),
(7, 'iΡhone 8', 1),
(7, 'iΡhone 8 Ρlus', 1);

INSERT INTO smartzfs.t_phone_model (type_id, name, dr) VALUES
(8, 'OnePlus 3', 1),
(8, 'OnePlus 3T', 1),
(8, 'OnePlus 5', 1),
(8, 'OnePlus 5T', 1),
(8, 'ONEPLUS 6', 1),
(8, 'OnePlus 6T', 1),
(8, 'OnePlus 7 Pro', 1),
(8, 'OnePlus 7T Pro迈凯伦限定版', 1),
(8, '一加7T', 1),
(8, '一加7T Pro', 1);
