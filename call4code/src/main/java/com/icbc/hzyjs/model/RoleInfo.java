package com.icbc.hzyjs.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "role_info")
@Setter
@Getter
public class RoleInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ROLE_INFO")
    @SequenceGenerator(sequenceName = "SEQ_ROLE_INFO", allocationSize = 1, name = "SEQ_ROLE_INFO")
    @Column(name = "role_id")
    private int roleId;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "role_authorization")
    private String roleAuthorization;

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
