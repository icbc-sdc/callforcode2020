create table city_general_info(
       city_id    					number(20) not null,
       city_name  					varchar2(20),
       city_risk				 	varchar2(20),
       congirmed_cases    			varchar2(50),
       suspected_cases		 		varchar2(50),
	   recovered_cases 				varchar2(50),
       death_cases    				varchar2(50),
       weather 						clob,
       traffic	    				clob,
	   polic						clob,
	   city_status					clob,
	   southwest_latitude			varchar2(20),
	   southwest_longitude			varchar2(20),
	   northeast_latitude			varchar2(20),
	   northeast_longitude			varchar2(20),
	   create_user_id 				varchar2(50),
       create_time    				timestamp(9),
       update_user_id 				varchar2(50),
       update_time    				timestamp(9),
	   is_avoid						varchar2(5)
);
  comment on table city_general_info
  is '城市舆情表';
  comment on column city_general_info.city_id
  is '城市ID';
  comment on column city_general_info.city_name
  is '城市名称';
  comment on column city_general_info.city_risk
  is '城市风险等级';
  comment on column city_general_info.congirmed_cases
  is '确诊人数';
  comment on column city_general_info.suspected_cases
  is '疑似人数';
  comment on column city_general_info.recovered_cases
  is '治愈人数';
  comment on column city_general_info.death_cases
  is '死亡人数';
  comment on column city_general_info.weather
  is '天气情况';
  comment on column city_general_info.traffic
  is '交通情况';
  comment on column city_general_info.polic
  is '政策情况';
  comment on column city_general_info.city_status
  is '城市管制情况';
  comment on column city_general_info.southwest_latitude
  is '城市西南端维度';
  comment on column city_general_info.southwest_longitude
  is '城市西南端经度';
  comment on column city_general_info.northeast_latitude
  is '城市东北端维度';
  comment on column city_general_info.northeast_longitude
  is '城市东北端经度';
  comment on column city_general_info.create_user_id
  is '创建用户ID';
  comment on column city_general_info.create_time
  is '创建时间';
  comment on column city_general_info.update_user_id
  is '更新用户ID';
  comment on column city_general_info.update_time
  is '更新时间';
  comment on column city_general_info.is_avoid
  is '是否需要避开';
  
CREATE UNIQUE INDEX INDEX_CITY_GENERAL_INFO ON CITY_GENERAL_INFO (city_id);
ALTER TABLE CITY_GENERAL_INFO ADD CONSTRAINT PK_CITY_GENERAL_INFO PRIMARY KEY (city_id) USING INDEX INDEX_CITY_GENERAL_INFO;


CREATE SEQUENCE SEQ_CITY_GENERAL_INFO
  MINVALUE 1
  MAXVALUE 9999999999
  START WITH 1000
  INCREMENT BY 1;