package com.whg.gmall.user.service.impl;

import com.whg.gmall.user.bean.UmsMember;
import com.whg.gmall.user.bean.UmsMemberReceiveAddress;
import com.whg.gmall.user.mapper.UmsMemberReceiveAddressDao;
import com.whg.gmall.user.mapper.UserDao;
import com.whg.gmall.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created by Administrator on 2020/9/26.
 */
@Service
public class UserServiceIml implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private UmsMemberReceiveAddressDao umsMemberReceiveAddressDao;

    @Override
    public List<UmsMember> getAllUser() {
//        List<UmsMember> umsMembers = userDao.getAllUser();
        List<UmsMember> umsMembers = userDao.selectAll();
        return umsMembers;
    }

    @Override
    public List<UmsMemberReceiveAddress> getReceiveAddressByMemberId(String memberId) {
        Example e = new Example(UmsMemberReceiveAddress.class);
        e.createCriteria().andEqualTo("memberId", memberId);
        List<UmsMemberReceiveAddress> umsMemberReceiveAddressList = umsMemberReceiveAddressDao.selectByExample(e);
        return umsMemberReceiveAddressList;
    }

    @Override
    public void addMember(UmsMember umsMember) {
        userDao.insert(umsMember);
    }
}
