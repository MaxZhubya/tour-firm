create table enterprises
(
	id bigint not null
		constraint enterprise_pkey
			primary key,
	some_value integer,
	some_string varchar(255),
	some_bool boolean
);

alter table enterprises owner to postgres;
