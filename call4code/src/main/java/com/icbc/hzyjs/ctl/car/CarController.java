package com.icbc.hzyjs.ctl.car;

import com.icbc.hzyjs.bean.ReturnBasic;
import com.icbc.hzyjs.model.VehicleInfo;
import com.icbc.hzyjs.repository.VehicleInfoRepository;
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
@RequestMapping("/car")
public class CarController {

    @Resource
    VehicleInfoRepository vehicleInfoRepository;

    @RequestMapping("/insert")
    private ReturnBasic<String> insert(@RequestBody VehicleInfo vehicleInfo) {
        vehicleInfo.setCreateTime(new Timestamp(System.currentTimeMillis()));
        vehicleInfo.setUpdateTime(vehicleInfo.getCreateTime());
        vehicleInfo.setUpdateUserId(vehicleInfo.getCreateUserId());
        vehicleInfo.setValidFlag("1");
        try {
            vehicleInfoRepository.saveAndFlush(vehicleInfo);
            return ReturnBasic.builder().success(true).message("operate success").data("-/-").build();
        } catch (Throwable throwable) {
            return ReturnBasic.builder().success(false).message(throwable.getMessage()).build();
        }
    }

    @RequestMapping("/query")
    private ReturnBasic<List<VehicleInfo>> query() {
        List<VehicleInfo> vehicleInfo = vehicleInfoRepository.findByValidFlag("1");
        if (vehicleInfo == null) {
            return ReturnBasic.builder().success(false).message("data not exits").build();
        }
        return ReturnBasic.builder().success(true).message("operate success").data(vehicleInfo).build();
    }

    @RequestMapping("/update")
    private ReturnBasic<String> update(@RequestBody VehicleInfo vehicleInfo) {
        try {
            VehicleInfo vehicleInfo1 = vehicleInfoRepository.findById(vehicleInfo.getVehicleId()).get();
            vehicleInfo.setCreateTime(vehicleInfo1.getCreateTime());
            vehicleInfo.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            PropertyUtil.copyProperties(vehicleInfo, vehicleInfo1);
            vehicleInfoRepository.save(vehicleInfo1);
            return ReturnBasic.builder().success(true).message("operate success").data("-/-").build();
        } catch (Throwable throwable) {
            return ReturnBasic.builder().success(false).message(throwable.getMessage()).build();
        }
    }

    @RequestMapping("/delete/{vehicleId}")
    private ReturnBasic<String> delete(@PathVariable("vehicleId")int vehicleId) {
        try {
            vehicleInfoRepository.deleteById(vehicleId);
            return ReturnBasic.builder().success(true).message("operate success").data("-/-").build();
        } catch (Throwable throwable) {
            return ReturnBasic.builder().success(false).message(throwable.getMessage()).build();
        }
    }
}
