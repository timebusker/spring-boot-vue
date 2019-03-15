-- noinspection SqlNoDataSourceInspection
CREATE TABLE sys_menu  (
  id bigint NOT NULL ,
  is_frame bit NULL DEFAULT NULL ,
  name varchar(255) NULL DEFAULT NULL ,
  component varchar(255) NULL DEFAULT NULL ,
  pid bigint NOT NULL,
  sort bigint NOT NULL,
  icon varchar(255) NULL DEFAULT NULL,
  url varchar(255) NULL DEFAULT NULL,
  create_time timestamptz NULL DEFAULT NULL,
  PRIMARY KEY (id)
);
comment on column sys_menu.id is '主键ID';
COMMENT on column sys_menu.is_frame is '是否外链';
COMMENT on column sys_menu.name is '菜单名称';
COMMENT on column sys_menu.component is '组件';
COMMENT on column sys_menu.pid is '上级菜单ID';
COMMENT on column sys_menu.sort is '排序';
COMMENT on column sys_menu.icon is '图标';
COMMENT on column sys_menu.url is '链接地址';
COMMENT on column sys_menu.create_time is '创建日期';