package com.icbc.hzyjs.repository;

import com.icbc.hzyjs.exception.BizException;
import com.icbc.hzyjs.model.RoleInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(rollbackFor = {RuntimeException.class, Error.class, BizException.class})
public interface RoleInfoRepository extends JpaRepository<RoleInfo, String>, JpaSpecificationExecutor<RoleInfo> {
}
