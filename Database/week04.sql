use bookstore;

select bookname, price from book;

select bookid, bookname, publisher, price from book;

select distinct publisher from book;

select * from book where price < 20000;

select * from book where 10000 <= price and price <= 20000;

select * from customer, orders where customer.custid = orders.custid;

select name, saleprice from customer natural join orders;

select name, bookname from customer natural join orders natural join book;

select name, bookname from customer natural join orders natural join book where price = 20000;

select * from customer as C, orders as O where C.custid = O.custid order by C.custid;

select bookname, publisher from book where bookname like '%축구%';

select * from book where bookname like '_구%';

select * from book where publisher like '굿스포츠' or publisher like '대한미디어';

select * from book order by bookname;

select * from book order by price desc, publisher;