-- noinspection SqlNoDataSourceInspectionForFile

-- 菜单目录表;
DROP TABLE IF EXISTS sys_menu;
CREATE TABLE sys_menu (
  id          BIGINT       NOT NULL,
  is_frame    INT          NOT NULL DEFAULT 0,
  name        VARCHAR(255) NOT NULL,
  component   VARCHAR(255) NULL     DEFAULT NULL,
  pid         BIGINT       NOT NULL,
  sort        BIGINT       NOT NULL,
  icon        VARCHAR(255) NULL     DEFAULT NULL,
  url         VARCHAR(255) NULL     DEFAULT NULL,
  create_time TIMESTAMPTZ  NULL     DEFAULT NULL,
  PRIMARY KEY (id)
);
COMMENT ON COLUMN sys_menu.id IS '主键id';
COMMENT ON COLUMN sys_menu.is_frame IS '是否外链';
COMMENT ON COLUMN sys_menu.name IS '菜单名称';
COMMENT ON COLUMN sys_menu.component IS '组件';
COMMENT ON COLUMN sys_menu.pid IS '上级菜单id';
COMMENT ON COLUMN sys_menu.sort IS '排序';
COMMENT ON COLUMN sys_menu.icon IS '图标';
COMMENT ON COLUMN sys_menu.url IS '链接地址';
COMMENT ON COLUMN sys_menu.create_time IS '创建日期';

-- 权限控制表;
DROP TABLE IF EXISTS sys_permission;
CREATE TABLE sys_permission (
  id          BIGINT       NOT NULL,
  alias       VARCHAR(255) NOT NULL,
  name        VARCHAR(255) NOT NULL,
  pid         BIGINT       NOT NULL,
  create_time TIMESTAMPTZ  NOT NULL,
  PRIMARY KEY (id)
);

COMMENT ON COLUMN sys_permission.id IS 'id主键';
COMMENT ON COLUMN sys_permission.alias IS '别名';
COMMENT ON COLUMN sys_permission.name IS '名称';
COMMENT ON COLUMN sys_permission.pid IS '上级id主键';
COMMENT ON COLUMN sys_permission.create_time IS '创建时间';

-- 角色表;
DROP TABLE IF EXISTS sys_role;
CREATE TABLE sys_role (
  id          BIGINT       NOT NULL,
  name        VARCHAR(255) NOT NULL,
  remark      VARCHAR(255) NOT NULL,
  create_time TIMESTAMPTZ  NOT NULL,
  PRIMARY KEY (id)
);

COMMENT ON COLUMN sys_role.id IS 'id主键';
COMMENT ON COLUMN sys_role.name IS '角色名称';
COMMENT ON COLUMN sys_role.remark IS '备注';
COMMENT ON COLUMN sys_role.create_time IS '创建时间';

-- 用户表;
DROP TABLE IF EXISTS sys_user;
CREATE TABLE sys_user (
  id                       BIGINT       NOT NULL,
  email                    VARCHAR(255),
  status                   INT          NOT NULL DEFAULT 1,
  password                 VARCHAR(255) NOT NULL,
  username                 VARCHAR(255) NOT NULL,
  last_password_reset_time TIMESTAMPTZ  NOT NULL,
  create_time              TIMESTAMPTZ  NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT sys_user_email UNIQUE (email),
  CONSTRAINT sys_user_username UNIQUE (username)
);

COMMENT ON COLUMN sys_user.id IS 'id主键';
COMMENT ON COLUMN sys_user.email IS '邮件地址';
COMMENT ON COLUMN sys_user.status IS '账户状态：1启用、0禁用';
COMMENT ON COLUMN sys_user.password IS '密码';
COMMENT ON COLUMN sys_user.username IS '用户/登录名';
COMMENT ON COLUMN sys_user.last_password_reset_time IS '最后修改密码的日期';
COMMENT ON COLUMN sys_user.create_time IS '创建时间';

-- 角色-菜单关系表;
DROP TABLE IF EXISTS sys_roles_menus;
CREATE TABLE sys_roles_menus (
  role_id BIGINT NOT NULL,
  menu_id BIGINT NOT NULL,
  PRIMARY KEY (menu_id, role_id)
);

COMMENT ON COLUMN sys_roles_menus.role_id IS '角色ID';
COMMENT ON COLUMN sys_roles_menus.menu_id IS '菜单id';

-- 角色-权限关系表;
DROP TABLE IF EXISTS sys_roles_permissions;
CREATE TABLE sys_roles_permissions (
  role_id       BIGINT NOT NULL,
  permission_id BIGINT NOT NULL,
  PRIMARY KEY (role_id, permission_id)
);

COMMENT ON COLUMN sys_roles_permissions.role_id IS '角色ID';
COMMENT ON COLUMN sys_roles_permissions.permission_id IS '权限id';

-- 用户-角色关系表;
DROP TABLE IF EXISTS sys_users_roles;
CREATE TABLE sys_users_roles (
  user_id BIGINT NOT NULL,
  role_id BIGINT NOT NULL,
  PRIMARY KEY (user_id, role_id)
);

COMMENT ON COLUMN sys_users_roles.role_id IS '角色id';
COMMENT ON COLUMN sys_users_roles.user_id IS '用户id';

DROP TABLE IF EXISTS file_image;
CREATE TABLE file_image (
  id          BIGINT        NOT NULL,
  name        VARCHAR(1024) NOT NULL,
  user_id        VARCHAR(64)   NOT NULL,
  url         VARCHAR(2048) NOT NULL,
  description        VARCHAR(2048),
  create_time TIMESTAMPTZ   NOT NULL,
  PRIMARY KEY (id)
);
COMMENT ON COLUMN file_image.id IS '主键id';
COMMENT ON COLUMN file_image.name IS '文件名称';
COMMENT ON COLUMN file_image.user_id IS '所有者';
COMMENT ON COLUMN file_image.description IS '文件描述';
COMMENT ON COLUMN file_image.create_time IS '上传时间';
-- 修改字段属性;
alter table file_image alter id type varchar(32);