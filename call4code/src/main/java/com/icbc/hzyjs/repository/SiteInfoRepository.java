package com.icbc.hzyjs.repository;

import com.icbc.hzyjs.model.SiteInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SiteInfoRepository extends JpaRepository<SiteInfo, String>, JpaSpecificationExecutor<SiteInfo> {
}
