create table member_info(
	m_id varchar2(10 char) primary key,
	m_pw varchar2(20 char) not null,
	m_name varchar2(10 char) not null,
	m_grade varchar2(10 char) not null,
	m_birthday date not null
)

create table notice(
	n_no number(4) primary key,
	n_writer varchar2(10 char) not null,
	n_title varchar2(50 char) not null,
	n_txt varchar2(500 char) not null,
	n_date date not null,
	constraint notice_writer
		foreign key n_writer
		references member_info(m_id)
)

create table notice_reply(
	nr_no number(4) primary key,
	nr_n_no number(4) not null,
	nr_writer varchar2(10 char) not null,
	nr_txt varchar2(500 char) not null,
	nr_date date not null,
	constraint n_reply
		foreign key nr_n_no
		references notice(n_no)
	constraint notice_reply_writer
		foreign key nr_writer
		references member_info(m_id)
)
