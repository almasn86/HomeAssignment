create table demo
(
   id integer not null auto_increment,
   full_url varchar(255) not null,
   short_url varchar(255) not null,
   description varchar(255),
   primary key(id)
);