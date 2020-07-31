package com.icbc.hzyjs.repository;

import com.icbc.hzyjs.exception.BizException;
import com.icbc.hzyjs.model.SiteInfo;
import com.icbc.hzyjs.model.UserInfo;
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
public interface SiteInfoRepository extends JpaRepository<SiteInfo, Integer>, JpaSpecificationExecutor<SiteInfo> {
    List<SiteInfo> findByValidFlag(String validFlag);
}
