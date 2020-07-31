package com.icbc.hzyjs.ctl.transfer;

import com.icbc.hzyjs.bean.ReturnBasic;
import com.icbc.hzyjs.model.LogisticsInfo;
import com.icbc.hzyjs.model.MaterialTransferInfo;
import com.icbc.hzyjs.repository.LogisticsInfoRepository;
import com.icbc.hzyjs.repository.MaterialTransferInfoRepository;
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
@RequestMapping("/transfer")
public class TransferController {
    @Resource
    MaterialTransferInfoRepository materialTransferInfoRepository;

    @RequestMapping("/insert")
    private ReturnBasic<String> insert(@RequestBody MaterialTransferInfo materialTransferInfo) {
        materialTransferInfo.setCreateTime(new Timestamp(System.currentTimeMillis()));
        materialTransferInfo.setUpdateTime(materialTransferInfo.getCreateTime());
        materialTransferInfo.setUpdateUserId(materialTransferInfo.getCreateUserId());
        materialTransferInfo.setStatus("1");
        try {
            materialTransferInfoRepository.saveAndFlush(materialTransferInfo);
            return ReturnBasic.builder().success(true).message("operate success").data("-/-").build();
        } catch (Throwable throwable) {
            return ReturnBasic.builder().success(false).message(throwable.getMessage()).build();
        }
    }

    @RequestMapping("/query")
    private ReturnBasic<List<MaterialTransferInfo>> query() {
        List<MaterialTransferInfo> materialTransferInfos = materialTransferInfoRepository.findAll();
        if (materialTransferInfos == null) {
            return ReturnBasic.builder().success(false).message("data not exits").build();
        }
        return ReturnBasic.builder().success(true).message("operate success").data(materialTransferInfos).build();
    }

    @RequestMapping("/update")
    private ReturnBasic<String> update(@RequestBody MaterialTransferInfo materialTransferInfo) {
        try {
            MaterialTransferInfo materialTransferInfo1 = materialTransferInfoRepository.findById(materialTransferInfo.getId()).get();
            materialTransferInfo.setCreateTime(materialTransferInfo1.getCreateTime());
            materialTransferInfo.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            PropertyUtil.copyProperties(materialTransferInfo, materialTransferInfo1);
            materialTransferInfoRepository.save(materialTransferInfo1);
            return ReturnBasic.builder().success(true).message("operate success").data("-/-").build();
        } catch (Throwable throwable) {
            return ReturnBasic.builder().success(false).message(throwable.getMessage()).build();
        }
    }

    @RequestMapping("/delete/{id}")
    private ReturnBasic<String> delete(@PathVariable("id")int id) {
        try {
            materialTransferInfoRepository.deleteById(id);
            return ReturnBasic.builder().success(true).message("operate success").data("-/-").build();
        } catch (Throwable throwable) {
            return ReturnBasic.builder().success(false).message(throwable.getMessage()).build();
        }
    }
}
