package com.icbc.hzyjs.repository;

import com.icbc.hzyjs.exception.BizException;
import com.icbc.hzyjs.model.CityGeneralInfo;
import com.icbc.hzyjs.model.LogisticsInfo;
import com.icbc.hzyjs.model.SiteInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author kfzx-zhoux.
 * @date 2020-07
 * @description
 */
@Repository
@Transactional(rollbackFor = {RuntimeException.class, Error.class, BizException.class})
public interface CityGeneralInfoRepository extends JpaRepository<CityGeneralInfo, Integer>, JpaSpecificationExecutor<CityGeneralInfo> {
}
