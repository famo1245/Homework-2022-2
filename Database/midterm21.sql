/*a)*/
create database movie;
use movie;

create table Star(Name varchar(20) primary key,
Age int,
Gender char);

create table Producer (Num char(2) primary key,
Name varchar(20),
Gender char,
Address varchar(10));

create table Studio (Name varchar(20) primary key,
Address varchar(10),
Phone char(8));

create table Movie (Title varchar(20),
Year int,
Length int,
Studio_Name varchar(20),
Producer_Num char(2),
primary key (Title, Year),
foreign key (Studio_Name) references Studio(Name),
foreign key (Producer_Num) references Producer(Num));

create table StarsIn (Movie_Title varchar(20),
Movie_Year int,
Star_Name varchar(20),
primary key (Movie_Title, Movie_Year, Star_Name),
foreign key (Movie_Title, Movie_Year) references Movie(Title, Year),
foreign key (Star_Name) references Star(Name));

insert into Studio values("SM Studio", "Korea", "111-1234");
insert into Studio values("Disney Studio", "USA", "222-1234");
insert into Studio values("Fox Studio", "USA", "333-1234");

insert into Star values("Rober Downey", 48, "M");
insert into Star values("Chris Evans", 60, "M");
insert into Star values("Brad Pitt", 48, "M");
insert into Star values("Angelina Jolie", 48, "F");
insert into Star values("Elsa", null, "F");
insert into Star values("Anna", null, "F");
insert into Star values("Jennifer", 45, "F");

insert into Producer values("P1", "Lucas", "M", "USA");
insert into Producer values("P2", "Spielberg", "M", "USA");
insert into Producer values("P3", "Lucas", "F", "USA");
insert into Producer values("P4", "Jennifer", "F", "USA");

insert into Movie values ("Star Wars", 1977, 95, "Fox Studio", "P1");
insert into Movie values ("Star Wars", 2010, 130, "Fox Studio", "P1");
insert into Movie values ("Star Wars", 2017, 120, "Fox Studio", "P2");
insert into Movie values ("Moana", 1990, 110, "Disney Studio", "P2");
insert into Movie values ("Lion King", 1990, 120, "Disney Studio", "P3");
insert into Movie values ("Lion King", 1995, 120, "Disney Studio", "P4");
insert into Movie values ("Frozen", 2013, 109, "SM Studio", "P4");
insert into Movie values ("Frozen", 2015, 109, "SM Studio", "P1");

insert into StarsIn values ("Star Wars", 1977, "Rober Downey");
insert into StarsIn values ("Star Wars", 1977, "Chris Evans");
insert into StarsIn values ("Star Wars", 2010, "Rober Downey");
insert into StarsIn values ("Star Wars", 2010, "Chris Evans");
insert into StarsIn values ("Star Wars", 2010, "Angelina Jolie");
insert into StarsIn values ("Star Wars", 2017, "Elsa");
insert into StarsIn values ("Frozen", 2013, "Elsa");
insert into StarsIn values ("Frozen", 2015, "Elsa");

/*b)*/
select Title from Movie where Length > 115;

--c)
select Name, Address from Producer as p where exists (select Producer_Num from Movie as m where Studio_Name="Disney Studio" and Year=1990 and p.Num=m.Producer_Num);

--d)
select Name from Star where Age is null;

--e)
select distinct Name from Star join StarsIn on Name=Star_Name where Movie_Title="Star Wars" order by Age desc, Name;

--f)
select Studio_Name, count(*) as total_num_of_movie from Movie group by Studio_Name;

--g)
select Name, Address, Phone, (select count(*) from Movie where Name=Studio_Name) as total_num_of_movie from Studio;

--h)
select Name, sum(Length) as total_length from Movie join Producer on Producer_Num=Num group by Name having count(*) >= 2;

--i)
select Name, total_length from (select Name, sum(Length) as total_length, count(*) as cnt from Movie join Producer on Producer_Num=Num group by Name) as j where cnt >= 2;
