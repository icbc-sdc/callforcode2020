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
@Table(name = "logistics_info")
@Setter
@Getter
public class LogisticsInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_LOGISTICS_INFO")
    @SequenceGenerator(sequenceName = "SEQ_LOGISTICS_INFO", allocationSize = 1, name = "SEQ_LOGISTICS_INFO")
    @Column(name = "logistics_id")
    private int logisticsId;

    @Column(name = "logistics_name")
    private String logisticsName;

    @Column(name = "material_id")
    private int materialId;

    @Column(name = "material_number")
    private String materialNumber;

    @Column(name = "vehicle_id")
    private String vehicleId;

    @Column(name = "site_id_start")
    private String siteIdStart;

    @Column(name = "site_id_end")
    private String siteIdEnd;

    @Column(name = "start_time")
    private Timestamp startTime;

    @Column(name = "end_time")
    private Timestamp endTime;

    @Column(name = "create_time")
    private Timestamp createTime;

    @Column(name = "create_user_id")
    private String createUserId;

    @Column(name = "update_time")
    private Timestamp updateTime;

    @Column(name = "update_user_id")
    private String updateUserId;

    @Column(name = "routing_info")
    private String routingInfo;

    @Column(name = "driver_info")
    private String driverInfo;
}
