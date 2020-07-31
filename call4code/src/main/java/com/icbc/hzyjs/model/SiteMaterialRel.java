package com.icbc.hzyjs.model;

import javax.persistence.Column;
import java.sql.Timestamp;

/**
 * @author kfzx-zhoux.
 * @date 2020-07
 * @description
 */
public class SiteMaterialRel {

    @Column(name = "site_id")
    private int siteId;

    @Column(name = "material_id")
    private int materialId;

    @Column(name = "create_user_id")
    private String createUserId;

    @Column(name = "create_time")
    private Timestamp createTime;

    @Column(name = "update_user_id")
    private String updateUserId;

    @Column(name = "update_time")
    private Timestamp updateTime;

    @Column(name = "status")
    private String status;

}
