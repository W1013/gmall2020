package com.whg.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.whg.gmall.bean.PmsBaseCatalog1;
import com.whg.gmall.bean.PmsBaseCatalog2;
import com.whg.gmall.bean.PmsBaseCatalog3;
import com.whg.gmall.bean.UmsMemberReceiveAddress;
import com.whg.gmall.manage.dao.PmsBaseCatalog1Dao;
import com.whg.gmall.manage.dao.PmsBaseCatalog2Dao;
import com.whg.gmall.manage.dao.PmsBaseCatalog3Dao;
import com.whg.gmall.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created by Administrator on 2020/10/6.
 */
@Service
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    PmsBaseCatalog1Dao pmsBaseCatalog1Dao;
    @Autowired
    PmsBaseCatalog2Dao pmsBaseCatalog2Dao;
    @Autowired
    PmsBaseCatalog3Dao pmsBaseCatalog3Dao;
    @Override
    public List<PmsBaseCatalog1> getCatalog1() {
        List<PmsBaseCatalog1> catalog1s = pmsBaseCatalog1Dao.selectAll();
        return catalog1s;
    }

    @Override
    public List<PmsBaseCatalog2> getCatalog2(String catalog1Id) {
        Example e = new Example(PmsBaseCatalog2.class);
        e.createCriteria().andEqualTo("catalog1Id", catalog1Id);
        List<PmsBaseCatalog2> pmsBaseCatalog2s = pmsBaseCatalog2Dao.selectByExample(e);
        return pmsBaseCatalog2s;
    }

    @Override
    public List<PmsBaseCatalog3> getCatalog3(String catalog2Id) {
        Example e = new Example(PmsBaseCatalog3.class);
        e.createCriteria().andEqualTo("catalog2Id", catalog2Id);
        List<PmsBaseCatalog3> pmsBaseCatalog3s = pmsBaseCatalog3Dao.selectByExample(e);
        return pmsBaseCatalog3s;
    }
}
