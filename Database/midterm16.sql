--a)
create database boatbook;

use boatbook;

create table Sailors(sid int,
sname varchar(10),
rating int,
age float,
primary key(sid));

create table Boats(bid int,
bname varchar(10),
color varchar(10),
primary key(bid));

create table Reserves(sid int,
bid int,
day Date,
primary key(sid, bid, day),
foreign key(sid) references Sailors(sid),
foreign key(bid) references Boats(bid));

insert Sailors values(22, "Dustin", 7, 45.0);
insert Sailors values(31, "Lubber", 8, 55.5);
insert Sailors values(64, "Horatio", 7, 35.0);

insert Boats values(101, "Interlake", "blue");
insert Boats values(102, "Interlake", "Red");
insert Boats values(103, "Clipper", "Red");

insert Reserves values(22, 101, '2016-10-10');
insert Reserves values(22, 102, '2016-10-10');
insert Reserves values(22, 103, '2016-10-08');
insert Reserves values(31, 102, '2016-11-10');
insert Reserves values(31, 103, '2016-11-10');
insert Reserves values(64, 101, '2016-11-08');

--b)
select color from Boats natural join Reserves natural join Sailors as s where s.sname="Lubber";

--d)
select sname from Sailors as S where exists (select sname from Sailors natural join Boats natural join Reserves as R where S.sid=R.bid and R.bid=103);

--e)
select r.bid, count(*) from Reserves as r natural join Boats as b where color="red" group by bid;

--f)
select rating, avg(age) as average from Sailors as s1 group by rating having count(rating) >= 2;

--g)
select sname from Sailors as s1 where s1.rating >= all (select rating from Sailors);
