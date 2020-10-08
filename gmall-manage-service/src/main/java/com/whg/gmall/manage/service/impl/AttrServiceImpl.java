package com.whg.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.whg.gmall.bean.PmsBaseAttrInfo;
import com.whg.gmall.bean.PmsBaseAttrValue;
import com.whg.gmall.bean.PmsBaseSaleAttr;
import com.whg.gmall.bean.PmsProductSaleAttr;
import com.whg.gmall.manage.dao.*;
import com.whg.gmall.service.AttrService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;

/**
 * Created by Administrator on 2020/10/6.
 */
@Service
public class AttrServiceImpl implements AttrService {
    @Autowired
    PmsBaseAttrInfoDao pmsBaseAttrInfoDao;

    @Autowired
    PmsBaseAttrValueDao pmsBaseAttrValueDao;

    @Autowired
    PmsBaseSaleAttrDao pmsBaseSaleAttrDao;

    @Autowired
    PmsProductSaleAttrDao pmsProductSaleAttrDao;

    @Autowired
    PmsProductSaleAttrValueDao pmsProductSaleAttrValueDao;

    @Override
    public List<PmsBaseAttrInfo> attrInfoList(String catalog3Id) {
        Example example = new Example(PmsBaseAttrInfo.class);
        example.createCriteria().andEqualTo("catalog3Id", catalog3Id);
        List<PmsBaseAttrInfo> pmsBaseAttrInfos = pmsBaseAttrInfoDao.selectByExample(example);
        pmsBaseAttrInfos.forEach(pmsBaseAttrInfo -> {
            Example valueExample = new Example(PmsBaseAttrValue.class);
            valueExample.createCriteria().andEqualTo("attrId",pmsBaseAttrInfo.getId());
            List<PmsBaseAttrValue> pmsBaseAttrValues = pmsBaseAttrValueDao.selectByExample(valueExample);
            pmsBaseAttrInfo.setAttrValueList(pmsBaseAttrValues);
        });
        return pmsBaseAttrInfos;
    }

    @Override
    public String saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo) {
        //id为空添加，id不为空修改
        if (StringUtil.isEmpty(pmsBaseAttrInfo.getId())) {
            try {
                //保存属性
                pmsBaseAttrInfoDao.insertSelective(pmsBaseAttrInfo);
                pmsBaseAttrInfo.getAttrValueList().forEach(pmsBaseAttrValue -> {
                    //保存属性值
                    pmsBaseAttrValue.setAttrId(pmsBaseAttrInfo.getId());
                    pmsBaseAttrValueDao.insertSelective(pmsBaseAttrValue);
                });
                return "success";
            } catch (Exception e) {
                return "fail";
            }
        } else {
            try {
                //属性
                pmsBaseAttrInfoDao.updateByPrimaryKeySelective(pmsBaseAttrInfo);
                // 属性值
                //先删后添
                Example example = new Example(PmsBaseAttrValue.class);
                example.createCriteria().andEqualTo("attrId", pmsBaseAttrInfo.getId());
                pmsBaseAttrValueDao.deleteByExample(example);
                pmsBaseAttrInfo.getAttrValueList().forEach(pmsBaseAttrValue -> {
                    pmsBaseAttrValue.setAttrId(pmsBaseAttrInfo.getId());
                    pmsBaseAttrValueDao.insertSelective(pmsBaseAttrValue);
                });
                return "success";
            } catch (Exception e) {
                return "fail";
            }
        }
    }

    @Override
    public List<PmsBaseAttrValue> getAttrValueList(String attrId) {
        Example example = new Example(PmsBaseAttrValue.class);
        example.createCriteria().andEqualTo("attrId", attrId);
        List<PmsBaseAttrValue> pmsBaseAttrValues = pmsBaseAttrValueDao.selectByExample(example);
        return pmsBaseAttrValues;
    }

    @Override
    public List<PmsBaseSaleAttr> baseSaleAttrList() {
        List<PmsBaseSaleAttr> pmsBaseSaleAttrs = pmsBaseSaleAttrDao.selectAll();
        return pmsBaseSaleAttrs;
    }
}
