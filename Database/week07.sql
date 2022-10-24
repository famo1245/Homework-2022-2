use bookstore;

show create table orders;	/* 테이블의 정의 볼 수 있음
constraint 옆에는 system에서 붙여준 제약조건 이름*/

alter table orders drop foreign key orders_ibfk_1;
alter table orders drop foreign key orders_ibfk_2;
/* 각각의 제약조건을 삭제 */

show create table orders;	/* foreign key의 제약 조건이 없어진것을 확인 */

alter table orders 
add foreign key (custid) references customer(custid) 
on delete cascade
on update cascade;

alter table orders 
add foreign key (bookid) references book(bookid) 
on delete cascade
on update cascade;
/* cascade를 추가한 제약조건 추가 */

show create table orders;	/* 추가된 제약조건 확인 */

delete from customer where name='박지성';

select * from customer;
select * from orders;

update customer set custid='12' where name='김연아';

select * from customer;
select * from orders;

delete from book where publisher='굿스포츠';

select * from book;
select * from orders;

select name, sum(saleprice) as 총액
from customer left outer join orders on
customer.custid=orders.custid
group by orders.custid;

create view vw_Customer as
select *
from customer
where address like '%대한민국%';

create view orders_customer(orderid, name, bookname, saleprice) as
select orderid, name, bookname, saleprice
from customer natural join orders natural join book;

select orderid, bookname, saleprice
from orders_customer
where name='김연아';

drop view vw_Customer;
