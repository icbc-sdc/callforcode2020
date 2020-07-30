package com.icbc.hzyjs.ctl.city;

import com.icbc.hzyjs.bean.ReturnBasic;
import com.icbc.hzyjs.model.CityGeneralInfo;
import com.icbc.hzyjs.model.VehicleInfo;
import com.icbc.hzyjs.repository.CityGeneralInfoRepository;
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
@RequestMapping("/city")
public class CityController {

    @Resource
    CityGeneralInfoRepository cityGeneralInfoRepository;

    @RequestMapping("/insert")
    private ReturnBasic<String> insert(@RequestBody CityGeneralInfo cityGeneralInfo) {
        cityGeneralInfo.setCreateTime(new Timestamp(System.currentTimeMillis()));
        cityGeneralInfo.setUpdateTime(cityGeneralInfo.getCreateTime());
        cityGeneralInfo.setUpdateUserId(cityGeneralInfo.getCreateUserId());
        try {
            cityGeneralInfoRepository.saveAndFlush(cityGeneralInfo);
            return ReturnBasic.builder().success(true).message("operate success").data("-/-").build();
        } catch (Throwable throwable) {
            return ReturnBasic.builder().success(false).message(throwable.getMessage()).build();
        }
    }

    @RequestMapping("/query")
    private ReturnBasic<List<CityGeneralInfo>> query() {
        List<CityGeneralInfo> cityGeneralInfos = cityGeneralInfoRepository.findAll();
        if (cityGeneralInfos == null) {
            return ReturnBasic.builder().success(false).message("data not exits").build();
        }
        return ReturnBasic.builder().success(true).message("operate success").data(cityGeneralInfos).build();
    }

    @RequestMapping("/update")
    private ReturnBasic<String> update(@RequestBody CityGeneralInfo cityGeneralInfo) {
        try {
            CityGeneralInfo cityGeneralInfo1 = cityGeneralInfoRepository.findById(cityGeneralInfo.getCityId()).get();
            cityGeneralInfo.setCreateTime(cityGeneralInfo1.getCreateTime());
            cityGeneralInfo.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            PropertyUtil.copyProperties(cityGeneralInfo, cityGeneralInfo1);
            cityGeneralInfoRepository.save(cityGeneralInfo1);
            return ReturnBasic.builder().success(true).message("operate success").data("-/-").build();
        } catch (Throwable throwable) {
            return ReturnBasic.builder().success(false).message(throwable.getMessage()).build();
        }
    }

    @RequestMapping("/delete/{cityId}")
    private ReturnBasic<String> delete(@PathVariable("cityId")int cityId) {
        try {
            cityGeneralInfoRepository.deleteById(cityId);
            return ReturnBasic.builder().success(true).message("operate success").data("-/-").build();
        } catch (Throwable throwable) {
            return ReturnBasic.builder().success(false).message(throwable.getMessage()).build();
        }
    }
}
