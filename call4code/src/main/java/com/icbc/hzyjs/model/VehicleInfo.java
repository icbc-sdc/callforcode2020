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
@Table(name = "vehicle_info")
@Setter
@Getter
public class VehicleInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VEHICLE_INFO")
    @SequenceGenerator(sequenceName = "SEQ_VEHICLE_INFO", allocationSize = 1, name = "SEQ_VEHICLE_INFO")
    @Column(name = "vehicle_id")
    private int vehicleId;

    @Column(name = "vehicle_number")
    private int vehicleNumber;

    @Column(name = "vehicle_travel_license")
    private String vehicleTravelLicense;

    @Column(name = "vehicle_owner")
    private String vehicleOwner;

    @Column(name = "vehicle_owner_phone")
    private String vehicleOwnerPhone;

    @Column(name = "create_time")
    private Timestamp createTime;

    @Column(name = "create_user_id")
    private String createUserId;

    @Column(name = "update_time")
    private Timestamp updateTime;

    @Column(name = "update_user_id")
    private String updateUserId;

    @Column(name = "vehicle_type")
    private String vehicleType;

    @Column(name = "valid_flag")
    private String validFlag;
}
