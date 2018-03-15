-- spring security自定义表格

-- 用户表
create table sec_id_user(
	id int primary key,
    username varchar(50) not null,
    password varchar(50) not null
    
    
);
-- 角色表
create table sec_id_role (
    id int primary key,
    name varchar(50) not null
);

-- 权限表
create table sec_id_permission (
    id int primary key,
    name varchar(50) not null,
    description varchar(200),
    url varchar(100),
    pid int
);

-- 用户角色表
create table sec_user_role (
    id int primary key,
    user_id int,
    role_id int

);

-- 角色权限表
create table sec_role_permisson (
    id int primary key,
    role_id int,
    permission_id int
);

insert into sec_id_user (id,username, password) values (1,'admin', 'admin');
insert into sec_id_user (id,username, password) values (2,'abel', 'abel');

insert into sec_id_role(id,name) values(1,'ROLE_ADMIN');
insert into sec_id_role(id,name) values(2,'ROLE_USER');

insert into sec_user_role(id,USER_ID,ROLE_ID) values(1,1,1);
insert into sec_user_role(id,USER_ID,ROLE_ID) values(2,2,2);

BEGIN;
INSERT INTO `sec_id_permission` VALUES ('1', 'ROLE_HOME', 'home', '/', null), ('2', 'ROLE_ADMIN', 'ABel', '/admin', null);
COMMIT;

BEGIN;
INSERT INTO `sec_role_permisson` VALUES ('1', '1', '1'), ('2', '1', '2'), ('3', '2', '1');
COMMIT;