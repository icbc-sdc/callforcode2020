package com.icbc.hzyjs.ctl.role;

import com.icbc.hzyjs.bean.ReturnBasic;
import com.icbc.hzyjs.model.RoleInfo;
import com.icbc.hzyjs.model.UserInfo;
import com.icbc.hzyjs.repository.RoleInfoRepository;
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
@RequestMapping("/role")
public class RoleController {

    @Resource
    RoleInfoRepository roleInfoRepository;

    @RequestMapping("/insert")
    private ReturnBasic<String> insert(@RequestBody RoleInfo roleInfo) {
        roleInfo.setCreateTime(new Timestamp(System.currentTimeMillis()));
        roleInfo.setUpdateTime(roleInfo.getCreateTime());
        roleInfo.setUpdateUserId(roleInfo.getCreateUserId());
        roleInfo.setStatus("1");
        try {
            roleInfoRepository.saveAndFlush(roleInfo);
            return ReturnBasic.builder().success(true).message("operate success").data("-/-").build();
        } catch (Throwable throwable) {
            return ReturnBasic.builder().success(false).message(throwable.getMessage()).build();
        }
    }

    @RequestMapping("/query")
    private ReturnBasic<List<RoleInfo>> query() {
        List<RoleInfo> roleInfo = roleInfoRepository.findByStatus("1");
        if (roleInfo == null) {
            return ReturnBasic.builder().success(false).message("data not exits").build();
        }
        return ReturnBasic.builder().success(true).message("operate success").data(roleInfo).build();
    }

    @RequestMapping("/update")
    private ReturnBasic<String> update(@RequestBody RoleInfo roleInfo) {
        try {
            RoleInfo roleInfo1 = roleInfoRepository.findById(roleInfo.getRoleId()).get();
            roleInfo.setCreateTime(roleInfo1.getCreateTime());
            roleInfo.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            PropertyUtil.copyProperties(roleInfo, roleInfo1);
            roleInfoRepository.save(roleInfo1);
            return ReturnBasic.builder().success(true).message("operate success").data("-/-").build();
        } catch (Throwable throwable) {
            return ReturnBasic.builder().success(false).message(throwable.getMessage()).build();
        }
    }

    @RequestMapping("/delete/{roleId}")
    private ReturnBasic<String> delete(@PathVariable("roleId")int roleId) {
        try {
            roleInfoRepository.deleteById(roleId);
            return ReturnBasic.builder().success(true).message("operate success").data("-/-").build();
        } catch (Throwable throwable) {
            return ReturnBasic.builder().success(false).message(throwable.getMessage()).build();
        }
    }
}
