--=================
--관리자계정
--================
create user mybatis
IDENTIFIED by mybatis
DEFAULT TABLESPACE users;

grant connect, resource to mybatis;

--=================
--mybatis 계정
--=================
create table student(
no number,
name varchar2(100) not null,
tel char(11) not null,
reg_date date default sysdate,
CONSTRAINT pk_student_no primary key(no)
);

create SEQUENCE seq_student_no;

select * from student;