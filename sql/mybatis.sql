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

--oracle synonym 객체
--동의어 객체. 별칭 객체

--mybatis계정에서 kh계정의 table접근
--존재하나 접근권한이 없어서 불가능
select * from kh.employee; --emp
select * from kh.department; --dept
select * from kh.job; --job

--동의어 생성
--resource roll에 포함되지 않은 권한
create SYNONYM emp for kh.employee;
create SYNONYM dept for kh.department;
create SYNONYM job for kh.job;

select * from emp;

--========================
--관리자계정
--========================
grant all on kh.employee to mybatis;
grant select on kh.department to mybatis;
grant select on kh.job to mybatis;
--동의어 생성 권한 주기
grant create SYNONYM to mybatis;
--========================