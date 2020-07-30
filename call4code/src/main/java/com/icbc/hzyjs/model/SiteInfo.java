package com.icbc.hzyjs.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "site_info")
@Setter
@Getter
public class SiteInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SITE_INFO")
    @SequenceGenerator(sequenceName = "SEQ_SITE_INFO", allocationSize = 1, name = "SEQ_SITE_INFO")
    @Column(name = "site_id")
    private int siteId;

    @Column(name = "site_name")
    private String siteName;

    @Column(name = "create_time")
    private Timestamp createTime;

    @Column(name = "create_user_id")
    private String createUserId;

    @Column(name = "update_time")
    private Timestamp updateTime;

    @Column(name = "update_user_id")
    private String updateUserId;

    @Column(name = "valid_flag")
    private String validFlag;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;
}
