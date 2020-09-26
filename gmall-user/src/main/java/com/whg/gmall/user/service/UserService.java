package com.whg.gmall.user.service;

import com.whg.gmall.user.bean.UmsMember;
import com.whg.gmall.user.bean.UmsMemberReceiveAddress;

import java.util.List;

/**
 * Created by Administrator on 2020/9/26.
 */
public interface UserService {
    List<UmsMember> getAllUser();

    List<UmsMemberReceiveAddress> getReceiveAddressByMemberId(String memberId);

}
