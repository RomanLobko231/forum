create sequence roles_seq start with 1 increment by 1;

create table roles (
role_id integer not null,
authority varchar(255),
primary key (role_id)
);

create table users (
id uuid not null,
email varchar(255),
password varchar(255),
username varchar(255) unique,
primary key (id)
);

create table user_role_junction (
role_id integer not null,
user_id uuid not null,
constraint FKhybpcwvq8snjhbxawo25hxous foreign key (role_id) references roles,
constraint FK5aqfsa7i8mxrr51gtbpcvp0v1 foreign key (user_id) references users,
primary key (role_id, user_id)
);
