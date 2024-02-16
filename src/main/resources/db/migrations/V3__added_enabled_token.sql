alter table users
add column is_enabled BOOLEAN default false,
add column verification_token varchar(255);
