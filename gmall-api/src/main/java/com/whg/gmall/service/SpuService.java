package com.whg.gmall.service;

import com.whg.gmall.bean.PmsProductImage;
import com.whg.gmall.bean.PmsProductInfo;
import com.whg.gmall.bean.PmsProductSaleAttr;

import java.util.List;

/**
 * Created by Administrator on 2020/10/7.
 */
public interface SpuService {
    List<PmsProductInfo> spuList(String catalog3Id);

    String saveSupInfo(PmsProductInfo pmsProductInfo);

    List<PmsProductSaleAttr> spuSaleAttrList(String spuId);

    List<PmsProductImage> spuImageList(String spuId);
}
