package com.whg.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.whg.gmall.bean.*;
import com.whg.gmall.manage.dao.*;
import com.whg.gmall.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created by Administrator on 2020/10/7.
 */
@Service
public class SpuServiceImpl implements SpuService {
    @Autowired
    PmsProductInfoDao pmsProductInfoDao;
    @Autowired
    PmsProductImageDao pmsProductImageDao;
    @Autowired
    PmsProductSaleAttrDao pmsProductSaleAttrDao;
    @Autowired
    PmsProductSaleAttrValueDao pmsProductSaleAttrValueDao;

    @Override
    public List<PmsProductInfo> spuList(String catalog3Id) {
        Example example = new Example(PmsProductInfo.class);
        example.createCriteria().andEqualTo("catalog3Id", catalog3Id);
        List<PmsProductInfo> pmsProductInfos = pmsProductInfoDao.selectByExample(example);
        return pmsProductInfos;
    }

    @Override
    public String saveSupInfo(PmsProductInfo pmsProductInfo) {
        //保存商品信息
        pmsProductInfoDao.insertSelective(pmsProductInfo);
        //保存商品图片信息
        pmsProductInfo.getSpuImageList().forEach(pmsProductImage -> {
            pmsProductImage.setProductId(pmsProductInfo.getId());
            pmsProductImageDao.insertSelective(pmsProductImage);
        });
        //保存销售属性信息
        pmsProductInfo.getSpuSaleAttrList().forEach(pmsProductSaleAttr -> {
            pmsProductSaleAttr.setProductId(pmsProductInfo.getId());
            pmsProductSaleAttrDao.insertSelective(pmsProductSaleAttr);
            //保存销售属性值信息
            pmsProductSaleAttr.getSpuSaleAttrValueList().forEach(pmsProductSaleAttrValue -> {
                pmsProductSaleAttrValue.setProductId(pmsProductInfo.getId());
                pmsProductSaleAttrValueDao.insertSelective(pmsProductSaleAttrValue);
            });
        });
        return "success";
    }

    @Override
    public List<PmsProductSaleAttr> spuSaleAttrList(String spuId) {
        Example example = new Example(PmsProductSaleAttr.class);
        example.createCriteria().andEqualTo("productId", spuId);
        List<PmsProductSaleAttr> pmsProductSaleAttrs = pmsProductSaleAttrDao.selectByExample(example);
        pmsProductSaleAttrs.forEach(pmsProductSaleAttr -> {
            PmsProductSaleAttrValue pmsProductSaleAttrValue = new PmsProductSaleAttrValue();
            pmsProductSaleAttrValue.setProductId(spuId);
            pmsProductSaleAttrValue.setSaleAttrId(pmsProductSaleAttr.getSaleAttrId());
            List<PmsProductSaleAttrValue> pmsProductSaleAttrValues = pmsProductSaleAttrValueDao.select(pmsProductSaleAttrValue);
            pmsProductSaleAttr.setSpuSaleAttrValueList(pmsProductSaleAttrValues);
        });
        return pmsProductSaleAttrs;
    }

    @Override
    public List<PmsProductImage> spuImageList(String spuId) {
        Example example = new Example(PmsProductImage.class);
        example.createCriteria().andEqualTo("productId",spuId);
        List<PmsProductImage> pmsProductImages = pmsProductImageDao.selectByExample(example);
        return pmsProductImages;
    }
}
