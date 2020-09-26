package com.whg.gmall.user.mapper;

import com.whg.gmall.user.bean.UmsMember;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by Administrator on 2020/9/26.
 */
public interface UserDao extends Mapper<UmsMember> {
    List<UmsMember> getAllUser();
}
