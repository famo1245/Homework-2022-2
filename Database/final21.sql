create database company;

use company;

create table department(Dnumber int primary key,
DName varchar(20));

create table employee(Name varchar(10),
SSN char(5) primary key,
address varchar(20),
salary int,
Dno int,
foreign key(Dno)
references department(Dnumber));

create table dependent(D_SSN char(5),
D_Name varchar(20),
relationship varchar(10),
primary key(D_SSN, D_Name),
foreign key(D_SSN)
references employee(SSN));

insert into department values(1, 'Research');
insert into department values(2, 'Administration');
insert into department values(3, 'H.Q');

insert into employee values('Adams', '11111', 'Houston, Tx', 35000, 1);
insert into employee values('Curry', '33333', NULL, 30000, 3);
insert into employee values('Green', '55555', 'Tempe, AZ', 90000, 2);
insert into employee values('Hayes', '66666', 'Los Angeles, CA', 50000, 3);
insert into employee values('Johnson', '77777', 'Los Angeles, CA', 29000, 1);
insert into employee values('Jones', '88888', NULL, 40000, 2);

insert into dependent values('11111', 'Jeniffer', 'Daughter');
insert into dependent values('11111', 'Joyce', 'Daughter');
insert into dependent values('33333', 'Borg', 'Son');
insert into dependent values('55555', 'Ahmad', 'Son');
insert into dependent values('55555', 'John', 'Son');
insert into dependent values('66666', 'Jane', 'Daughter');

select Name, e.DName, salary
from (select DName, avg(salary) as avg
from employee join department
on Dno=Dnumber
group by DName) as dm
join
(select Name, DName, salary
from employee join department
on Dno=Dnumber
) as e
on dm.DName=e.DName
where salary > avg;

select *
from (select DName, avg(salary) as avg
from employee join department
on Dno=Dnumber
group by DName) as dm
join
(select Name, DName, salary
from employee join department
on Dno=Dnumber
) as e
on dm.DName=e.DName
where salary > avg;