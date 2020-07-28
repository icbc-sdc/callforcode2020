package com.icbc.hzyjs.repository;

import com.icbc.hzyjs.model.SiteInfo;
import com.icbc.hzyjs.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.jws.soap.SOAPBinding;
import java.util.List;

/**
 * @author kfzx-zhoux.
 * @date 2020-07-28
 * @description
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer>, JpaSpecificationExecutor<UserInfo>  {

    UserInfo findByUserIdAndUserName(int userId, String userName);

    List<UserInfo> findByValidFlag(String validFlag);
}
