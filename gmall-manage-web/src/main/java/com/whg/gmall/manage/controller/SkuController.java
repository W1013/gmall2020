package com.whg.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.whg.gmall.bean.PmsSkuInfo;
import com.whg.gmall.service.SkuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2020/10/7.
 */
@Controller
@CrossOrigin
public class SkuController {

    @Reference
    SkuService skuService;

    @RequestMapping("saveSkuInfo")
    @ResponseBody
    public String saveSkuInfo(@RequestBody PmsSkuInfo pmsSkuInfo) {

        String success = skuService.saveSkuInfo(pmsSkuInfo);
        return success;
    }
}
