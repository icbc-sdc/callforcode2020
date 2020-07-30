create table role_info(
       role_id    			number(20) not null,
       role_name  			varchar2(50),
       role_authorization   varchar2(50),
       create_user_id 		varchar2(50),
       create_time    		timestamp(9),
       update_user_id 		varchar2(50),
       update_time    		timestamp(9),
	   status		  		varchar2(5)
);
  comment on table role_info
  is '角色信息表';
  comment on column role_info.role_id
  is '角色ID';
  comment on column role_info.role_name
  is '角色名称';
  comment on column role_info.role_authorization
  is '角色权限范围';
  comment on column role_info.create_user_id
  is '创建用户ID';
  comment on column role_info.create_time
  is '创建时间';
  comment on column role_info.update_user_id
  is '更新用户ID';
  comment on column role_info.update_time
  is '更新时间';
  comment on column role_info.status
  is '角色状态（0有效，1无效）';
  
CREATE UNIQUE INDEX INDEX_ROLE_INFO ON ROLE_INFO (ROLE_ID);
ALTER TABLE ROLE_INFO ADD CONSTRAINT PK_ROLE_INFO PRIMARY KEY (ROLE_ID) USING INDEX INDEX_ROLE_INFO;


CREATE SEQUENCE SEQ_ROLE_INFO
  MINVALUE 1
  MAXVALUE 9999999999
  START WITH 1000
  INCREMENT BY 1;