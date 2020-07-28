package com.icbc.hzyjs.model;

import lombok.Getter;
import lombok.Setter;
import oracle.sql.CLOB;

import javax.persistence.*;

/**
 * @author kfzx-zhoux.
 * @date 2020-07
 * @description
 */
@Entity
@Table(name = "logistics_operation_Info")
@Setter
@Getter
public class LogisticsOperationInfo {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private String id;

    @Column(name = "logistics_id")
    private String logisticsId;

    @Column(name = "sample_position")
    private String samplePosition;

    @Column(name = "sample_latitude")
    private String sampleLatitude;

    @Column(name = "sample_longitude")
    private String sampleLongitude;

    @Column(name = "status")
    private String status;

    @Column(name = "comment")
    private CLOB comment;

}
