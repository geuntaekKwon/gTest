-- SEBank (2017/6/15)
DROP TABLE customer;

-- 1. Customer
CREATE TABLE customer
(
	custid varchar2(20) constraint customer_custid_pk  PRIMARY KEY,
	password varchar2(20) constraint customer_pw_nn not null,
	name varchar2(30) not null,
	email varchar2(30),
	division varchar2(30) not null, -- 고객구분 : personal(개인), company(기업)
	idno varchar2(20), 				-- 개인 :주민, 기업 : 사업자등록번호 
	address varchar2(100)
);
DROP TABLE reply;
DROP TABLE board;

DROP SEQUENCE board_seq;
DROP SEQUENCE reply_seq;

-- 2. Board
CREATE TABLE board 
(
	boardnum number PRIMARY KEY,
	custid varchar2(20) NOT NULL,
	title varchar2(100) NOT NULL,
	content varchar2(4000) NOT NULL,
	inputdate date default sysdate,
	hits number default 0,
	originalfile varchar2(300),
	savedfile varchar2(300)
);


CREATE SEQUENCE board_seq start with 1 increment by 1;

-- 3. Reply
CREATE TABLE reply
(
	replynum number PRIMARY KEY,
	boardnum number NOT NULL,
	custid varchar2(20) NOT NULL,
	text varchar2(200)  NOT NULL,
	inputdate date default sysdate,
	
		constraint reply_boardnum_fk FOREIGN KEY(boardnum)
		references board(boardnum) on delete cascade
);

CREATE SEQUENCE reply_seq start with 1 increment by 1;




