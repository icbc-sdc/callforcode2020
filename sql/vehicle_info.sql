create table vehicle_info(
       vehicle_id    				number(20) not null,
       vehicle_number  				number(20),
       vehicle_travel_license 		varchar2(50),
       vehicle_owner    			varchar2(50),
       vehicle_owner_phone 			varchar2(20),
	   create_user_id 				varchar2(50),
       create_time    				timestamp(9),
       update_user_id 				varchar2(50),
       update_time    				timestamp(9),
	   vehicle_type					varchar2(50),
	   valid_flag					varchar2(5)
);
  comment on table vehicle_info
  is '车辆信息表';
  comment on column vehicle_info.vehicle_id
  is '车辆ID';
  comment on column vehicle_info.vehicle_number
  is '车牌号';
  comment on column vehicle_info.vehicle_travel_license
  is '行驶证';
  comment on column vehicle_info.vehicle_owner
  is '车辆所有人';
  comment on column vehicle_info.vehicle_owner_phone
  is '车辆所有人联系方式';
  comment on column vehicle_info.create_user_id
  is '创建用户ID';
  comment on column vehicle_info.create_time
  is '创建时间';
  comment on column vehicle_info.update_user_id
  is '更新用户ID';
  comment on column vehicle_info.update_time
  is '更新时间';
  comment on column vehicle_info.vehicle_type
  is '车辆类型';
  comment on column vehicle_info.valid_flag
  is '有效标志（1有效，0无效）';
  
CREATE UNIQUE INDEX INDEX_VEHICLE_INFO ON VEHICLE_INFO (vehicle_id);
ALTER TABLE VEHICLE_INFO ADD CONSTRAINT PK_VEHICLE_INFO PRIMARY KEY (vehicle_id) USING INDEX INDEX_VEHICLE_INFO;


CREATE SEQUENCE SEQ_VEHICLE_INFO
  MINVALUE 1
  MAXVALUE 9999999999
  START WITH 1000
  INCREMENT BY 1;