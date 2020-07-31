create table logistics_info(
       logistics_id    				number(20) not null,
       logistics_name  				varchar2(50),
       material_id			 		number(20),
       material_number    			number(20),
       vehicle_id		 			number(20),
	   site_id_start 				varchar2(50),
       site_id_end    				varchar2(50),
       start_time 					timestamp(9),
       end_time    					timestamp(9),
	   create_user_id 				varchar2(50),
       create_time    				timestamp(9),
       update_user_id 				varchar2(50),
       update_time    				timestamp(9),
	   routing_info					CLOB,
	   driver_info					CLOB
);
  comment on table logistics_info
  is '物流信息表';
  comment on column logistics_info.logistics_id
  is '物流ID';
  comment on column logistics_info.logistics_name
  is '物流名称';
  comment on column logistics_info.material_id
  is '物流货物ID';
  comment on column logistics_info.material_number
  is '物流货物件数';
  comment on column logistics_info.vehicle_id
  is '车辆ID';
  comment on column logistics_info.site_id_start
  is '出发站点ID';
  comment on column logistics_info.site_id_end
  is '目标站点ID';
  comment on column logistics_info.start_time
  is '出发时间';
  comment on column logistics_info.end_time
  is '到达时间';
  comment on column logistics_info.create_user_id
  is '创建用户ID';
  comment on column logistics_info.create_time
  is '创建时间';
  comment on column logistics_info.update_user_id
  is '更新用户ID';
  comment on column logistics_info.update_time
  is '更新时间';
  comment on column logistics_info.routing_info
  is '路径信息';
  comment on column logistics_info.driver_info
  is '司机信息';
  
CREATE UNIQUE INDEX INDEX_LOGISTICS_INFO ON LOGISTICS_INFO (logistics_id);
ALTER TABLE LOGISTICS_INFO ADD CONSTRAINT PK_LOGISTICS_INFO PRIMARY KEY (logistics_id) USING INDEX INDEX_LOGISTICS_INFO;


CREATE SEQUENCE SEQ_LOGISTICS_INFO
  MINVALUE 1
  MAXVALUE 9999999999
  START WITH 1000
  INCREMENT BY 1;