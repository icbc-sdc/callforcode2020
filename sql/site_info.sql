create table site_info(
       site_id    			number(20) not null,
       site_name  			varchar2(50),
       create_user_id 		varchar2(50),
       create_time    		timestamp(9),
       update_user_id 		varchar2(50),
       update_time    		timestamp(9),
	   valid_flag			varchar2(5),
	   latitude				varchar2(20),
	   longitude			varchar2(20)
);
  comment on table site_info
  is '站点信息表';
  comment on column site_info.site_id
  is '站点编号';
  comment on column site_info.site_name
  is '站点名称';
  comment on column site_info.create_user_id
  is '创建用户ID';
  comment on column site_info.create_time
  is '创建时间';
  comment on column site_info.update_user_id
  is '更新用户ID';
  comment on column site_info.update_time
  is '更新时间';
  comment on column site_info.valid_flag
  is '有效标志';
  comment on column site_info.latitude
  is '站点纬度';
  comment on column site_info.longitude
  is '站点经度';
  
CREATE UNIQUE INDEX INDEX_SITE_INFO ON site_info (SITE_ID);
ALTER TABLE site_info ADD CONSTRAINT PK_SITE_INFO PRIMARY KEY (SITE_ID) USING INDEX INDEX_SITE_INFO;


CREATE SEQUENCE SEQ_SITE_INFO
  MINVALUE 1
  MAXVALUE 9999999999
  START WITH 1000
  INCREMENT BY 1;