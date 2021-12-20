delete from customer;
delete from customer_order;
delete from book_order;
delete from books_ordered;
delete from book;
delete from publisher;
delete from author;
delete from address;

insert into address values ('a1a1a1', '111', 'elm avenue', '1', 'ottawa', 'ontario');
insert into address values ('b2b2b2', '222', 'elm avenue', null, 'toronto', 'ontario');
insert into address values ('c3c3c3', '111', 'oak avenue', '2', 'ottawa', 'ontario');
insert into address values ('d4d4d4', '333', 'spruce avenue', null, 'victoria', 'british columbia');
insert into address values ('e5e5e5', '444', 'pine avenue', null, 'edmonton', 'alberta');
insert into address values ('f6f6f6', '111', 'birch avenue', null, 'whitehorse', 'yukon');

insert into author values ('janeausten@gmail.com', 'jane', 'austen');
insert into author values ('harperlee@gmail.com', 'harper', 'lee');
insert into author values ('fscottfitzgerald@gmail.com', 'francis', 'scott fitzgerald');
insert into author values ('hermanmelville@gmail.com', 'herman', 'melville');


insert into publisher values ('1111111111', 'penguin pooks', 'penguin@gmail.com', '111111111', '1111111', '20', 'e5e5e5');
insert into publisher values ('2222222222', 'arrow books', 'arrow@gmail.com', '222222222', '2222222', '50', 'f6f6f6');
insert into publisher values ('3333333333', 'harpercollins', 'harpercollins@gmail.com', '333333333', '3333333', '100', 'a1a1a1');

insert into customer values ('admin@gmail.com', 'adminpassword', 'admin', 'istrator', null, null, null, null, null);
insert into customer values ('bilbobaggins@gmail.com', 'precious', 'bilbo', 'baggins', '1111111111111111', '111', 'bilbo', 'baggins', 'a1a1a1');
insert into customer values ('frodobaggins@gmail.com', 'omwtodestroyring', 'frodo', 'baggins', '2222222222222222', '222', 'bilbo', 'baggins', 'b2b2b2');
insert into customer values ('samwisegamgee@gmail.com', 'ifollowfrodo', 'samwise', 'gamgee', '3333333333333333', '333', 'samwise', 'gamgee', 'c3c3c3');

insert into book values ('9780141199078', 'pride and prejudice', 'classics', '416', '9.99', '20', '50', '5', '2012-12-06', '1111111111', 'janeausten@gmail.com');
insert into book values ('9781784752637', 'to kill a mockingbird', 'classics', '320', '8.99', '25', '60', '6', '2015-06-04', '1111111111', 'harperlee@gmail.com');
insert into book values ('9780141182636', 'the great gatsby', 'classics', '240', '7.99', '30', '60', '7', '2000-02-24', '2222222222', 'fscottfitzgerald@gmail.com');
insert into book values ('9780141197692', 'persuasion', 'classics', '288', '7.99', '30', '65', '8', '2011-11-03', '3333333333', 'janeausten@gmail.com');
insert into book values ('9780142437247', 'moby-dick', 'whales', '720', '14.99', '10', '70', '9', '2003-02-27', '3333333333', 'hermanmelville@gmail.com');

insert into book_order values ('1', '26.97', '2021-12-01', '1111111111111111', '111', 'bilbo', 'baggins', 'b2b2b2');
insert into book_order values ('2', '17.98', '2021-12-02', '1111111111111111', '111', 'bilbo', 'baggins', 'b2b2b2');
insert into book_order values ('3', '29.98', '2021-12-03', '4444444444444444', '444', 'samwise', 'gamgee', 'd4d4d4');

insert into customer_order values ('0000000001', 'bilbobaggins@gmail.com');
insert into customer_order values ('0000000002', 'bilbobaggins@gmail.com');
insert into customer_order values ('0000000003', 'samwisegamgee@gmail.com');

insert into books_ordered values ('9780141199078', '0000000001', '1');
insert into books_ordered values ('9781784752637', '0000000001', '1');
insert into books_ordered values ('9780141182636', '0000000001', '1');
insert into books_ordered values ('9780141199078', '0000000002', '1');
insert into books_ordered values ('9780141197692', '0000000002', '1');
insert into books_ordered values ('9780142437247', '0000000003', '2');
