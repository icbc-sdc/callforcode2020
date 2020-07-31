package com.icbc.hzyjs.repository;

import com.icbc.hzyjs.exception.BizException;
import com.icbc.hzyjs.model.LogisticsInfo;
import com.icbc.hzyjs.model.VehicleInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author kfzx-zhoux.
 * @date 2020-07
 * @description
 */
@Repository
@Transactional(rollbackFor = {RuntimeException.class, Error.class, BizException.class})
public interface LogisticsInfoRepository extends JpaRepository<LogisticsInfo, Integer>, JpaSpecificationExecutor<LogisticsInfo> {

}
