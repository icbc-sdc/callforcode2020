package com.icbc.hzyjs.repository;

import com.icbc.hzyjs.exception.BizException;
import com.icbc.hzyjs.model.MaterialInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(rollbackFor = {RuntimeException.class, Error.class, BizException.class})
public interface MaterialInfoRepository extends JpaRepository<MaterialInfo, String>, JpaSpecificationExecutor<MaterialInfo> {
}
