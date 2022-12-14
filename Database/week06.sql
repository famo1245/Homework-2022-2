use bookstore;
select custid, (select count(*) from orders as T where S.custid=T.custid)  as 도서수량, (select sum(saleprice) from orders as K where K.custid=S.custid) as 총액 from customer as S;
select book.bookname from book where book.price > (select avg(price) from book as T where book.publisher = T.publisher);
select C.name, C.address from customer as C where C.custid in (select O.custid from orders as O where C.custid=O.custid);
insert into book(bookname, publisher, price) values(11, '스포츠 의학', '한솔의학서적', 90000);
update customer set address='대한민국 부산' where custid=5;
update customer set address='대한민국 서울' where name='박세리';
delete from customer where custid=5;
delete from customer; /*삭제 안됨, foreign key 로 참조되고 있는 tuples이 있기 때문*/