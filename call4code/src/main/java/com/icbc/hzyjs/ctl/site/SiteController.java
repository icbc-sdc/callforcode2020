package com.icbc.hzyjs.ctl.site;

import com.icbc.hzyjs.bean.ReturnBasic;
import com.icbc.hzyjs.model.SiteInfo;
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
@RequestMapping("/site")
public class SiteController {

    @Resource
    SiteInfoRepository siteInfoRepository;

    @RequestMapping("/insert")
    private ReturnBasic<String> insert(@RequestBody SiteInfo siteInfo) {
        siteInfo.setCreateTime(new Timestamp(System.currentTimeMillis()));
        siteInfo.setUpdateTime(siteInfo.getCreateTime());
        siteInfo.setUpdateUserId(siteInfo.getCreateUserId());
        siteInfo.setValidFlag("1");
        try {
            siteInfoRepository.saveAndFlush(siteInfo);
            return ReturnBasic.builder().success(true).message("operate success").data("-/-").build();
        } catch (Throwable throwable) {
            return ReturnBasic.builder().success(false).message(throwable.getMessage()).build();
        }
    }

    @RequestMapping("/query")
    private ReturnBasic<List<SiteInfo>> query() {
        List<SiteInfo> siteInfo = siteInfoRepository.findByValidFlag("1");
        if (siteInfo == null) {
            return ReturnBasic.builder().success(false).message("data not exits").build();
        }
        return ReturnBasic.builder().success(true).message("operate success").data(siteInfo).build();
    }

    @RequestMapping("/update")
    private ReturnBasic<String> update(@RequestBody SiteInfo siteInfo) {
        try {
            SiteInfo siteInfo1 = siteInfoRepository.findById(siteInfo.getSiteId()).get();
            siteInfo.setCreateTime(siteInfo1.getCreateTime());
            siteInfo.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            PropertyUtil.copyProperties(siteInfo, siteInfo1);
            siteInfoRepository.save(siteInfo1);
            return ReturnBasic.builder().success(true).message("operate success").data("-/-").build();
        } catch (Throwable throwable) {
            return ReturnBasic.builder().success(false).message(throwable.getMessage()).build();
        }
    }

    @RequestMapping("/delete/{siteId}")
    private ReturnBasic<String> delete(@PathVariable("siteId")int siteId) {
        try {
            siteInfoRepository.deleteById(siteId);
            return ReturnBasic.builder().success(true).message("operate success").data("-/-").build();
        } catch (Throwable throwable) {
            return ReturnBasic.builder().success(false).message(throwable.getMessage()).build();
        }
    }

}
