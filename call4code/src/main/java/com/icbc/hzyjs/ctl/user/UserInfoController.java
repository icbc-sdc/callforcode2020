package com.icbc.hzyjs.ctl.user;

import com.icbc.hzyjs.bean.ReturnBasic;
import com.icbc.hzyjs.model.UserInfo;
import com.icbc.hzyjs.repository.UserInfoRepository;
import com.icbc.hzyjs.util.PropertyUtil;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author kfzx-zhoux.
 * @date 2020-07
 * @description
 */
@RestController
@RequestMapping("/user")
public class UserInfoController {

    @Resource
    UserInfoRepository userInfoRepository;

    @RequestMapping("/insert")
    private ReturnBasic<String> insert(@RequestBody UserInfo userInfo) {
        userInfo.setCreateTime(new Timestamp(System.currentTimeMillis()));
        userInfo.setUpdateTime(userInfo.getCreateTime());
        userInfo.setUpdateUserId(userInfo.getCreateUserId());
        userInfo.setValidFlag("1");
        try {
            userInfoRepository.saveAndFlush(userInfo);
            return ReturnBasic.builder().success(true).message("operate success").data("-/-").build();
        } catch (Throwable throwable) {
            return ReturnBasic.builder().success(false).message(throwable.getMessage()).build();
        }
    }

    @RequestMapping("/query")
    private ReturnBasic<List<UserInfo>> query() {
        List<UserInfo> userInfo = userInfoRepository.findByValidFlag("1");
        if (userInfo == null) {
            return ReturnBasic.builder().success(false).message("data not exits").build();
        }
        return ReturnBasic.builder().success(true).message("operate success").data(userInfo).build();
    }

    @RequestMapping("/query/{userId}/{userName}")
    private ReturnBasic<UserInfo> query(@PathVariable("userId")int userId, @PathVariable("userName") String userName) {
        UserInfo userInfo = userInfoRepository.findByUserIdAndUserName(userId,userName);
        if (userInfo == null) {
            return ReturnBasic.builder().success(false).message("data not exits").build();
        }
        return ReturnBasic.builder().success(true).message("operate success").data(userInfo).build();
    }

    @RequestMapping("/update")
    private ReturnBasic<String> update(@RequestBody UserInfo userInfo) {
        try {
            UserInfo userInfo1 = userInfoRepository.findById(userInfo.getUserId()).get();
            userInfo.setCreateTime(userInfo1.getCreateTime());
            userInfo.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            PropertyUtil.copyProperties(userInfo, userInfo1);
            userInfoRepository.save(userInfo1);
            return ReturnBasic.builder().success(true).message("operate success").data("-/-").build();
        } catch (Throwable throwable) {
            return ReturnBasic.builder().success(false).message(throwable.getMessage()).build();
        }
    }

    @RequestMapping("/delete/{userId}")
    private ReturnBasic<String> delete(@PathVariable("userId")int userId) {
        try {
            userInfoRepository.deleteById(userId);
            return ReturnBasic.builder().success(true).message("operate success").data("-/-").build();
        } catch (Throwable throwable) {
            return ReturnBasic.builder().success(false).message(throwable.getMessage()).build();
        }
    }
}