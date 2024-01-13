drop database if exists library;
create database library;

use library;

drop table if exists books;

create table books(
  uuid varchar(255),
  title varchar(255),
  author varchar(255),
  bstatus varchar(50),
  btype varchar(50),
  constraint pkBooks primary key (uuid)
);

