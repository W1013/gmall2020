package com.whg.gmall.service;

import com.whg.gmall.bean.PmsBaseAttrInfo;
import com.whg.gmall.bean.PmsBaseAttrValue;
import com.whg.gmall.bean.PmsBaseSaleAttr;
import com.whg.gmall.bean.PmsProductSaleAttr;

import java.util.List;

/**
 * Created by Administrator on 2020/10/6.
 */
public interface AttrService {
    List<PmsBaseAttrInfo> attrInfoList(String catalog3Id);

    String saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo);

    List<PmsBaseAttrValue> getAttrValueList(String attrId);

    List<PmsBaseSaleAttr> baseSaleAttrList();
}
