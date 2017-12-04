create table users (
  user_name varchar(50),
  password varchar(64),
  enable boolean default false
);
create table authorities(
  user_name varchar(50),
  authority varchar(64)
);
-- [jdbc-user-service]
-- select username, password, enabled from users where username = ?
-- select username, authority from authorities where username = ?
-- select g.id, g.group_name, ga.authority
--        from groups g, group_members gm, group_authorities ga
--        where gm.username = ? and g.id = ga.group_id and g.id = gm.group_id


create table groups(
  id int,
  group_name varchar(64)
);
create table groups_authorities(
  group_id int,
  authority varchar(64)
);
create table groups_numbers(
  id int,
  group_id int,
  username varchar(64)
);