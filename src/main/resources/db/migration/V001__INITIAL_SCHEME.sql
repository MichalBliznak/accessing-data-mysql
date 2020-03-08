create schema db_example collate latin2_czech_cs;

create table user
(
	id int not null
		primary key,
	email varchar(255) null,
	name varchar(255) null
);
