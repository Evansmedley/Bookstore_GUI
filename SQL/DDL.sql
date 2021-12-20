/*
drop table customer_order;
drop table books_ordered;
drop table book_order;
drop table customer;
drop table book;
drop table author;
drop table publisher;
drop table address;
*/

create table address
  (
    postal_code   varchar(6),
    street_number   numeric(5,0),
    street_name   varchar(128),
    apt_number    numeric(4,0),
    city          varchar(128),
    province      varchar(50) ,
    primary key(postal_code)
  );

create table author
  (
    email       varchar(50),
    first_name  varchar(50),
    last_name   varchar(50),
    primary key (email)
  );

create table publisher
  (
    phone_number    numeric(50,0),
    name        varchar(255),
    email       varchar(255),
    transit_number   numeric(9,0),
    account_number   numeric(7,0),
    money_owed       numeric(9,2) check (money_owed >= 0),
    postal_code      varchar(255),
    primary key(phone_number),
    foreign key(postal_code) references address on delete set null
  );

create table book
  (
    ISBN      numeric(13,0),
    title     varchar(255),
    genre     varchar(50),
    number_of_pages   numeric(7,0),
    price     numeric(9,2) check (price > 0),
    stock     int check (stock > 0),
    percentage_of_sales   numeric(3,0) check (percentage_of_sales >= 0),
    num_sold_prev_month   int check (num_sold_prev_month >= 0),
    date_published    date not null,
    publisher_phone_number  numeric(50,0),
    author_email      varchar(255),
    primary key(ISBN),
    foreign key(publisher_phone_number) references publisher on delete cascade,
    foreign key(author_email) references author on delete cascade
  );

create table book_order
  (
    order_number    serial,
    price           numeric(12,2),
    time_placed     date not null default current_date,
    credit_card_number  numeric(16,0),
    security_code       numeric(3,0),
    first_name_on_card  varchar(50),
    last_name_on_card   varchar(50),
    postal_code         varchar(6),
    primary key(order_number),
    foreign key(postal_code) references address on delete set null
  );

create table customer
  (
    email        varchar(255),
    password     varchar(20),
    first_name   varchar(50),
    last_name    varchar(50),
    credit_card_number   numeric(16,0),
    security_code     numeric(3,0),
    first_name_on_card   varchar(50),
    last_name_on_card    varchar(50),
    postal_code       varchar(6),
    primary key(email),
    foreign key(postal_code) references address on delete set null
  );

create table customer_order
  (
    order_number    int,
    email       varchar(255),
    primary key(order_number),
    foreign key(email) references customer on delete cascade,
    foreign key(order_number) references book_order on delete cascade
  );

create table books_ordered
  (
    ISBN      numeric(13,0),
    order_number    int,
    quantity_ordered  int check(quantity_ordered > 0),
    primary key(ISBN, order_number),
    foreign key(ISBN) references book on delete cascade,
    foreign key(order_number) references book_order on delete cascade
  );
