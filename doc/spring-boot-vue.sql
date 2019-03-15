-- 菜单目录表;
drop table if exists sys_menu;
create table sys_menu  (
  id bigint not null ,
  is_frame int not null default 0 ,
  name varchar(255) not null ,
  component varchar(255) null default null ,
  pid bigint not null,
  sort bigint not null,
  icon varchar(255) null default null,
  url varchar(255) null default null,
  create_time timestamptz null default null,
  primary key (id)
);
comment on column sys_menu.id is '主键id';
comment on column sys_menu.is_frame is '是否外链';
comment on column sys_menu.name is '菜单名称';
comment on column sys_menu.component is '组件';
comment on column sys_menu.pid is '上级菜单id';
comment on column sys_menu.sort is '排序';
comment on column sys_menu.icon is '图标';
comment on column sys_menu.url is '链接地址';
comment on column sys_menu.create_time is '创建日期';

-- 权限控制表;
drop table if exists sys_permission;
create table sys_permission  (
  id bigint not null ,
  alias varchar(255) not null ,
  name varchar(255) not null ,
  pid bigint not null ,
  create_time timestamptz not null,
  primary key (id)
);

comment on column sys_permission.id is 'id主键';
comment on column sys_permission.alias is '别名';
comment on column sys_permission.name is '名称';
comment on column sys_permission.pid is '上级id主键';
comment on column sys_permission.create_time is '创建时间';

-- 角色表;
drop table if exists sys_role;
create table sys_role  (
  id bigint not null ,
  name varchar(255) not null ,
  remark varchar(255) not null ,
  create_time timestamptz not null ,
  primary key (id)
);

comment on column sys_role.id is 'id主键';
comment on column sys_role.name is '角色名称';
comment on column sys_role.remark is '备注';
comment on column sys_role.create_time is '创建时间';

-- 用户表;
drop table if exists sys_user;
create table sys_user  (
  id bigint not null,
  email varchar(255) ,
  status int not null default 1 ,
  password varchar(255) not null ,
  username varchar(255) not null ,
  last_password_reset_time timestamptz not null ,
  create_time timestamptz not null ,
  primary key (id) ,
  constraint sys_user_email unique(email),
  constraint sys_user_username unique(username)
);

comment on column sys_user.id is 'id主键';
comment on column sys_user.email is '邮件地址';
comment on column sys_user.status is '账户状态：1启用、0禁用';
comment on column sys_user.password is '密码';
comment on column sys_user.username is '用户/登录名';
comment on column sys_user.last_password_reset_time is '最后修改密码的日期';
comment on column sys_user.create_time is '创建时间';

-- 角色-菜单关系表;
drop table if exists sys_roles_menus;
create table sys_roles_menus  (
  role_id bigint not null ,
  menu_id bigint not null ,
  primary key (menu_id, role_id)
);

comment on column sys_roles_menus.role_id is '角色ID';
comment on column sys_roles_menus.menu_id is '菜单id';

-- 角色-权限关系表;
DROP TABLE IF EXISTS sys_roles_permissions;
CREATE TABLE sys_roles_permissions  (
  role_id bigint NOT NULL ,
  permission_id bigint NOT NULL ,
  PRIMARY KEY (role_id, permission_id)
);

comment on column sys_roles_permissions.role_id is '角色ID';
comment on column sys_roles_permissions.permission_id is '权限id';

-- 用户-角色关系表;
drop table if exists sys_users_roles;
create table sys_users_roles  (
  user_id bigint not null ,
  role_id bigint not null ,
  primary key (user_id, role_id)
);

comment on column sys_users_roles.role_id is '角色id';
comment on column sys_users_roles.user_id is '用户id';