create table user_info(
       user_id    number(20) not null,
       user_name  varchar2(50),
       role_id    number(20),
       role_name  varchar2(50),
       create_user_id varchar2(50),
       create_time    timestamp(9),
       update_user_id varchar2(50),
       update_time    timestamp(9),
       dev_dept       varchar2(50),
       valid_flag     varchar2(10),
       phone          varchar2(50),
       email          varchar2(50)
);
  comment on table user_info
  is '用户信息表';
  comment on column user_info.user_id
  is '人员ID';
  comment on column user_info.user_name
  is '人员名称';
  comment on column user_info.role_id
  is '角色ID';
  comment on column user_info.role_name
  is '角色名称';
  comment on column user_info.create_user_id
  is '创建用户ID';
  comment on column user_info.create_time
  is '创建时间';
  comment on column user_info.update_user_id
  is '更新用户ID';
  comment on column user_info.update_time
  is '更新时间';
  comment on column user_info.dev_dept
  is '部门';
  comment on column user_info.valid_flag
  is '有效标志';
  comment on column user_info.phone
  is '电话';
  comment on column user_info.email
  is '邮件';
  
CREATE UNIQUE INDEX INDEX_USER_INFO ON USER_INFO (USER_ID);
ALTER TABLE USER_INFO ADD CONSTRAINT PK_USER_INFO PRIMARY KEY (USER_ID) USING INDEX INDEX_USER_INFO;


CREATE SEQUENCE SEQ_USER_INFO
  MINVALUE 1
  MAXVALUE 9999999999
  START WITH 1000
  INCREMENT BY 1;