-- 用户表
create table users(
    username varchar(50) not null primary key,
    password varchar(50) not null,
    enabled boolean not null
);
-- 权限表
create table authorities (
    username varchar(50) not null,
    authority varchar(50) not null,
    constraint fk_authorities_users foreign key(username) references users(username)
);
-- 唯一索引
create unique index ix_auth_username on authorities (username,authority);

-- 数据

insert into users(username,password,enabled) values('admin','admin',true);
insert into users(username,password,enabled) values('user','user',true);

insert into authorities(username,authority) values('admin','ROLE_ADMIN');
insert into authorities(username,authority) values('admin','ROLE_USER');
insert into authorities(username,authority) values('user','ROLE_USER');

/*
	1.users：用户表。包含username用户登录名，password登陆密码，enabled用户是否被禁用三个字段。
	
	其中username用户登录名为主键。 
	
	2.authorities：权限表。包含username用户登录名，authorities对应权限两个字段。
	
	其中username字段与users用户表的主键使用外键关联。
	
	3.对authorities权限表的username和authority创建唯一索引，提高查询效率
	
	Spring Security会在初始化时，从这两张表中获得用户信息和对应权限，将这些信息保存到缓存中。其中users表中的登录名和密码用来控制用户的登录，而权限表中的信息用来控制用户登陆后是否有权限访问受保护的系统资源。 
 */

        