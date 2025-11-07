#create schema attendance
create table attendance.member(
	id int auto_increment not null,
	name varchar(255) not null,
	lastname varchar(255) not null,
	biometricdata longtext not null,
	primary key (id)
);
create table attendance.event(
	id int auto_increment not null,
	name varchar(255) not null,
    primary key (id)
);

create table attendance.register(
	id bigint auto_increment not null,
	idmember int not null,
    idevent int not null,
    date datetime not null,
    foreign key (idevent) references attendance.event(id),
    foreign key (idmember) references attendance.member(id),
    primary key (id)
)

#---------------------
SELECT * FROM attendance.members
