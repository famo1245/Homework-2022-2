use bookstore;
select sum(saleprice) as 총매출 from orders;
select sum(saleprice) as 총매출 from orders where custid=2;
select sum(saleprice) as Total, avg(saleprice) as Average, min(saleprice) as Minimum, max(saleprice) as Maximum from orders;
select count(*) from orders;
select custid, count(*) as 도서수량, sum(saleprice) as 총액 from orders group by custid;
select custid, count(*) as 도서수량 from orders where saleprice >= 8000 group by custid having 도서수량 >= 2;
select bookname from book where price >= all(select price from book);
select bookname from book where price in (select max(price) from book);
select name from customer where custid in (select distinct custid from orders);
select bookname from (select publisher, avg(price) as avg from book group by publisher) as publisher_avg, book where book.publisher=publisher_avg.publisher and book.price > publisher_avg.avg;