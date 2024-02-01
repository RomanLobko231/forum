create table topic (
dislikes integer,
likes integer,
time_created timestamp(6) with time zone,
time_updated timestamp(6) with time zone,
id uuid not null,
message TEXT,
title varchar(255),
primary key (id)
);

create table image (
id bigserial not null,
image_size bigint not null,
image_name varchar(255),
image_bytes bytea,
primary key (id)
);

create table message (
time_created timestamp(6) with time zone,
time_updated timestamp(6) with time zone,
id uuid not null,
topic_id uuid not null,
message TEXT,
primary key (id),
foreign key (topic_id) references topic on delete cascade
);


create table topic_images (
images_id bigint not null unique,
topic_id uuid not null,
foreign key (topic_id) references topic,
foreign key (images_id) references image
);