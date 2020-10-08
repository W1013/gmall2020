package com.whg.gmall.service;

import com.whg.gmall.bean.PmsBaseCatalog1;
import com.whg.gmall.bean.PmsBaseCatalog2;
import com.whg.gmall.bean.PmsBaseCatalog3;

import java.util.List;

/**
 * Created by Administrator on 2020/10/6.
 */
public interface CatalogService {
    List<PmsBaseCatalog1> getCatalog1();

    List<PmsBaseCatalog2> getCatalog2(String catalog1Id);

    List<PmsBaseCatalog3> getCatalog3(String catalog2Id);
}

