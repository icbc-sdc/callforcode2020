package com.icbc.hzyjs.ctl.material;

import com.icbc.hzyjs.bean.ReturnBasic;
import com.icbc.hzyjs.model.MaterialInfo;
import com.icbc.hzyjs.model.SiteInfo;
import com.icbc.hzyjs.repository.MaterialInfoRepository;
import com.icbc.hzyjs.repository.SiteInfoRepository;
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
@RequestMapping("/material")
public class MaterialController {

    @Resource
    MaterialInfoRepository materialInfoRepository;

    @RequestMapping("/insert")
    private ReturnBasic<String> insert(@RequestBody MaterialInfo materialInfo) {
        materialInfo.setCreateTime(new Timestamp(System.currentTimeMillis()));
        materialInfo.setUpdateTime(materialInfo.getCreateTime());
        materialInfo.setUpdateUserId(materialInfo.getCreateUserId());
        materialInfo.setValidFlag("1");
        try {
            materialInfoRepository.saveAndFlush(materialInfo);
            return ReturnBasic.builder().success(true).message("operate success").data("-/-").build();
        } catch (Throwable throwable) {
            return ReturnBasic.builder().success(false).message(throwable.getMessage()).build();
        }
    }

    @RequestMapping("/query")
    private ReturnBasic<List<SiteInfo>> query() {
        List<MaterialInfo> materialInfo = materialInfoRepository.findByValidFlag("1");
        if (materialInfo == null) {
            return ReturnBasic.builder().success(false).message("data not exits").build();
        }
        return ReturnBasic.builder().success(true).message("operate success").data(materialInfo).build();
    }


    @RequestMapping("/update")
    private ReturnBasic<String> update(@RequestBody MaterialInfo materialInfo) {
        try {
            MaterialInfo materialInfo1 = materialInfoRepository.findById(materialInfo.getMaterialId()).get();
            materialInfo.setCreateTime(materialInfo1.getCreateTime());
            materialInfo.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            PropertyUtil.copyProperties(materialInfo, materialInfo1);
            materialInfoRepository.save(materialInfo1);
            return ReturnBasic.builder().success(true).message("operate success").data("-/-").build();
        } catch (Throwable throwable) {
            return ReturnBasic.builder().success(false).message(throwable.getMessage()).build();
        }
    }

    @RequestMapping("/delete/{materialId}")
    private ReturnBasic<String> delete(@PathVariable("materialId")int materialId) {
        try {
            materialInfoRepository.deleteById(materialId);
            return ReturnBasic.builder().success(true).message("operate success").data("-/-").build();
        } catch (Throwable throwable) {
            return ReturnBasic.builder().success(false).message(throwable.getMessage()).build();
        }
    }
}
