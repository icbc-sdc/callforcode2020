create table material_transfer_info(
       id    						number(20) not null,
       material_id  				number(20),
       material_number			 	number(20),
       start_site_id    			varchar2(50),
       end_site_id		 			varchar2(50),
	   create_user_id 				varchar2(50),
       create_time    				timestamp(9),
       update_user_id 				varchar2(50),
       update_time    				timestamp(9),
	   status						varchar2(5)
);
  comment on table material_transfer_info
  is '物资转移信息表';
  comment on column material_transfer_info.id
  is '主键ID';
  comment on column material_transfer_info.material_id
  is '货物ID';
  comment on column material_transfer_info.material_number
  is '货物件数';
  comment on column material_transfer_info.start_site_id
  is '发出站点编号';
  comment on column material_transfer_info.end_site_id
  is '接收站点编号';
  comment on column material_transfer_info.create_user_id
  is '创建用户ID';
  comment on column material_transfer_info.create_time
  is '创建时间';
  comment on column material_transfer_info.update_user_id
  is '更新用户ID';
  comment on column material_transfer_info.update_time
  is '更新时间';
  comment on column material_transfer_info.status
  is '状态';
  
CREATE UNIQUE INDEX INDEX_MATERIAL_TRANSFER_INFO ON MATERIAL_TRANSFER_INFO (id);
ALTER TABLE MATERIAL_TRANSFER_INFO ADD CONSTRAINT PK_MATERIAL_TRANSFER_INFO PRIMARY KEY (id) USING INDEX INDEX_MATERIAL_TRANSFER_INFO;


CREATE SEQUENCE SEQ_MATERIAL_TRANSFER_INFO
  MINVALUE 1
  MAXVALUE 9999999999
  START WITH 1000
  INCREMENT BY 1;