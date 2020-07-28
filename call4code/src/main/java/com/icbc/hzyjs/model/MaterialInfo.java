package com.icbc.hzyjs.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "material_info")
@Setter
@Getter
public class MaterialInfo {

    @Id
    @GeneratedValue
    @Column(name = "material_id")
    private String materialId;

    @Column(name = "material_name")
    private String materialName;

    @Column(name = "material_type")
    private String materialType;

    @Column(name = "material_number")
    private String materialNumber;

    @Column(name = "measure_unit")
    private String measureUnit;

    @Column(name = "measure_origin")
    private String measureOrigin;

    @Column(name = "measure_origin_user")
    private String measureOriginUser;

    @Column(name = "measure_origin_phone")
    private String measureOriginPhone;

    @Column(name = "create_user_id")
    private String createUserId;

    @Column(name = "create_time")
    private Timestamp createTime;

    @Column(name = "update_user_id")
    private String updateUserId;

    @Column(name = "update_time")
    private Timestamp updateTime;

    @Column(name = "valid_flag")
    private String validFlag;
}
