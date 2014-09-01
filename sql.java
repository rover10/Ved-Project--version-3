create table criminal_record
(criminal_id number(12) primary key,
fname varchar2(35),
lname varchar2(35),
mname varchar2(35),
father_name varchar2(70),
mother_name varchar2(70),
paddress varchar2(100),
caddress varchar2(100),
age number(3),
sex  varchar2(6),
height number(3),
weight number(4),
colour varchar2(35),
contact varchar2(40),
staffid number(8))
/



create table crime_record
(criminal_id number(10), fir_number number(12), record_date date, crime varchar2(250))
/
