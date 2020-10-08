package com.whg.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.whg.gmall.bean.PmsSkuAttrValue;
import com.whg.gmall.bean.PmsSkuImage;
import com.whg.gmall.bean.PmsSkuInfo;
import com.whg.gmall.bean.PmsSkuSaleAttrValue;
import com.whg.gmall.manage.dao.PmsSkuAttrValueDao;
import com.whg.gmall.manage.dao.PmsSkuImageDao;
import com.whg.gmall.manage.dao.PmsSkuInfoDao;
import com.whg.gmall.manage.dao.PmsSkuSaleAttrValueDao;
import com.whg.gmall.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.util.StringUtil;

/**
 * Created by Administrator on 2020/10/8.
 */
@Service
public class SkuServiceImpl implements SkuService {
    @Autowired
    PmsSkuInfoDao pmsSkuInfoDao;

    @Autowired
    PmsSkuImageDao pmsSkuImageDao;

    @Autowired
    PmsSkuAttrValueDao pmsSkuAttrValueDao;

    @Autowired
    PmsSkuSaleAttrValueDao pmsSkuSaleAttrValueDao;

    @Override
    public String saveSkuInfo(PmsSkuInfo pmsSkuInfo) {

        if (StringUtil.isEmpty(pmsSkuInfo.getSkuDefaultImg())) {
            String skuDefaultImg = pmsSkuInfo.getSkuImageList().size() != 0 ? pmsSkuInfo.getSkuImageList().get(0).getImgUrl() : null;
            pmsSkuInfo.setSkuDefaultImg(skuDefaultImg);
        }
        pmsSkuInfoDao.insertSelective(pmsSkuInfo);

        pmsSkuInfo.getSkuImageList().forEach(pmsSkuImage -> {
            pmsSkuImage.setSkuId(pmsSkuInfo.getId());
            pmsSkuImageDao.insertSelective(pmsSkuImage);
        });

        pmsSkuInfo.getSkuAttrValueList().forEach(pmsSkuAttrValue -> {
            pmsSkuAttrValue.setSkuId(pmsSkuInfo.getId());
            pmsSkuAttrValueDao.insertSelective(pmsSkuAttrValue);
        });
        pmsSkuInfo.getSkuSaleAttrValueList().forEach(pmsSkuSaleAttrValue -> {
            pmsSkuSaleAttrValue.setSkuId(pmsSkuInfo.getId());
            pmsSkuSaleAttrValueDao.insertSelective(pmsSkuSaleAttrValue);
        });
        return "success";
    }
}
