package com.icbc.hzyjs.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author kfzx-zhoux.
 * @date 2020-07-28
 * @description
 */
@Entity
@Table(name = "material_transfer_info")
@Setter
@Getter
public class MaterialTransferInfo {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private String id;

    @Column(name = "material_id")
    private String materialId;

    @Column(name = "material_number")
    private String materialNumber;

    @Column(name = "start_site_id")
    private String startSiteId;

    @Column(name = "end_site_id")
    private String endSiteId;

    @Column(name = "create_time")
    private Timestamp createTime;

    @Column(name = "create_user_id")
    private String createUserId;

    @Column(name = "update_time")
    private Timestamp updateTime;

    @Column(name = "update_user_id")
    private String updateUserId;

    @Column(name = "status")
    private String status;
}
