package com.icbc.hzyjs.model;

import lombok.Getter;
import lombok.Setter;
import oracle.sql.CLOB;

import javax.persistence.*;
import java.sql.Clob;
import java.sql.Timestamp;

/**
 * @author kfzx-zhoux.
 * @date 2020-07-28
 * @description
 */
@Entity
@Table(name = "city_general_info")
@Setter
@Getter
public class CityGeneralInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CITY_INFO")
    @SequenceGenerator(sequenceName = "SEQ_CITY_INFO", allocationSize = 1, name = "SEQ_CITY_INFO")
    @Column(name = "city_id")
    private int cityId;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "city_risk")
    private String cityRisk;

    @Column(name = "congirmed_cases")
    private String congirmedCases;

    @Column(name = "suspected_cases")
    private String suspectedCases;

    @Column(name = "recovered_cases")
    private String recoveredCases;

    @Column(name = "death_cases")
    private String deathCases;

    @Column(name = "weather")
    private CLOB weather;

    @Column(name = "traffic")
    private CLOB traffic;

    @Column(name = "polic")
    private CLOB polic;

    @Column(name = "city_status")
    private CLOB cityStatus;

    @Column(name = "southwest_latitude")
    private String southwestLatitude;

    @Column(name = "southwest_longitude")
    private String southwestLongitude;

    @Column(name = "northeast_latitude")
    private String northeastLatitude;

    @Column(name = "northeast_longitude")
    private String northeastLongitude;

    @Column(name = "create_time")
    private Timestamp createTime;

    @Column(name = "create_user_id")
    private String createUserId;

    @Column(name = "update_time")
    private Timestamp updateTime;

    @Column(name = "update_user_id")
    private String updateUserId;

    @Column(name = "is_avoid")
    private String  isAvoid;
}
