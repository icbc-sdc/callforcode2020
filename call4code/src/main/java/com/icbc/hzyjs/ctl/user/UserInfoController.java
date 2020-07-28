package com.icbc.hzyjs.ctl.user;

import com.icbc.hzyjs.model.UserInfo;
import com.icbc.hzyjs.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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

    @RequestMapping("/query")
    private List<UserInfo> query() {
        return userInfoRepository.findByValidFlag("1");
    }

    @RequestMapping("/query/{userId}/{userName}")
    private UserInfo query(@PathVariable("userId")int userId, @PathVariable("userName") String userName) {
        return userInfoRepository.findByUserIdAndUserName(userId,userName);
    }
}