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

-- 图片路径;
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

-- 文件上传临时表;
DROP TABLE IF EXISTS file_tmp;
CREATE TABLE file_tmp (
  id          varchar(32)   NOT NULL,
  url         VARCHAR(2048) NOT NULL,
  create_time TIMESTAMPTZ   NOT NULL,
  PRIMARY KEY (id)
);
COMMENT ON COLUMN file_tmp.id IS '主键id';
COMMENT ON COLUMN file_tmp.url IS '文件URL地址';
COMMENT ON COLUMN file_tmp.create_time IS '上传时间';


-- 秒杀商品信息表;
drop table if exists shop_goods;
create table shop_goods(
  id BIGINT not NULL,
  name VARCHAR(128) not null,
  title VARCHAR(1024) not null,
  image VARCHAR(128) not null,
  stock INT not NULL DEFAULT 0,
  price FLOAT not null DEFAULT 99999.99,
  start_time TIMESTAMPTZ NOT NULL,
  end_time TIMESTAMPTZ not null,
  PRIMARY KEY (id)
);

COMMENT ON column shop_goods.id is '商品主键ID';
COMMENT ON column shop_goods.name is '商品名称';
COMMENT ON column shop_goods.title is '商品标题';
COMMENT ON column shop_goods.image is '商品图片地址';
COMMENT ON column shop_goods.stock is '商品库存量';
COMMENT ON column shop_goods.price is '商品价格';
COMMENT ON column shop_goods.start_time is '秒杀开始时间';
COMMENT ON column shop_goods.end_time is '秒杀结束时间';

drop table if exists shop_order;
create table shop_order(
  id BIGINT not null,
  user_id BIGINT NOT NULL,
  goods_id BIGINT NOT NULL,
  count int NOT NULL,
  status int not NULL,
  amount FLOAT NOT NULL,
  pay_time timestamptz,
  pay_end_time timestamp NOT NULL,
  create_time timestamp NOT NULL,
  PRIMARY KEY (id)
);

COMMENT ON column shop_order.id is '订单主键ID';
COMMENT ON column shop_order.user_id is '用户ID';
COMMENT ON column shop_order.goods_id is '商品ID';
COMMENT ON column shop_order.count is '商品数量';
COMMENT ON column shop_order.amount is '订单金额';
COMMENT ON column shop_order.status is '订单状态';
COMMENT ON column shop_order.pay_time is '支付时间';
COMMENT ON column shop_order.pay_end_time is '支付截止时间';
COMMENT ON column shop_order.create_time is '订单创建时间';