create table material_info(
       material_id    		number(20) not null,
       material_name  		varchar2(50),
       material_type 		varchar2(50),
       material_number    	number(20),
       measure_unit 		varchar2(50),
       measure_origin    	varchar2(50),
	   measure_origin_user	varchar2(50),
	   measure_origin_phone	varchar2(50),
	   create_user_id 		varchar2(50),
       create_time    		timestamp(9),
       update_user_id 		varchar2(50),
       update_time    		timestamp(9),
	   valid_flag			varchar2(5)
);
  comment on table material_info
  is '物资信息表';
  comment on column material_info.material_id
  is '物资编号';
  comment on column material_info.material_name
  is '物资名称';
  comment on column material_info.material_type
  is '物资类型';
  comment on column material_info.material_number
  is '物资件数';
  comment on column material_info.measure_unit
  is '物资计量单位';
  comment on column material_info.measure_origin
  is '物资来源';
  comment on column material_info.measure_origin_user
  is '物资来源联系人';
  comment on column material_info.measure_origin_phone
  is '物资来源联系电话';
  comment on column material_info.create_user_id
  is '创建用户ID';
  comment on column material_info.create_time
  is '创建时间';
  comment on column material_info.update_user_id
  is '更新用户ID';
  comment on column material_info.update_time
  is '更新时间';
  comment on column material_info.valid_flag
  is '有效标志（1有效，0无效）';
  
CREATE UNIQUE INDEX INDEX_MATERIAL_INFO ON material_info (MATERIAL_ID);
ALTER TABLE material_info ADD CONSTRAINT PK_MATERIAL_INFO PRIMARY KEY (MATERIAL_ID) USING INDEX INDEX_MATERIAL_INFO;


CREATE SEQUENCE SEQ_MATERIAL_INFO
  MINVALUE 1
  MAXVALUE 9999999999
  START WITH 1000
  INCREMENT BY 1;