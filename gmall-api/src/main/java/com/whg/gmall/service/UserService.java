package com.whg.gmall.service;


import com.whg.gmall.bean.UmsMember;
import com.whg.gmall.bean.UmsMemberReceiveAddress;

import java.util.List;

/**
 * Created by Administrator on 2020/9/26.
 */
public interface UserService {
    List<UmsMember> getAllUser();

    List<UmsMemberReceiveAddress> getReceiveAddressByMemberId(String memberId);

    void addMember(UmsMember umsMember);
}
