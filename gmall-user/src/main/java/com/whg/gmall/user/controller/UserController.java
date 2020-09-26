package com.whg.gmall.user.controller;

import com.whg.gmall.user.bean.UmsMember;
import com.whg.gmall.user.bean.UmsMemberReceiveAddress;
import com.whg.gmall.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Administrator on 2020/9/26.
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("index")
    public String index() {
        return "hello user";
    }

    @RequestMapping("getReceiveAddressByMemberId")
    public List<UmsMemberReceiveAddress> getReceiveAddressByMemberId(String memberId) {
        List<UmsMemberReceiveAddress> umsMemberReceiveAddressList = userService.getReceiveAddressByMemberId(memberId);
        return umsMemberReceiveAddressList;
    }

    @RequestMapping("addMember")
    public void addMember(@RequestBody UmsMember umsMember) {
        userService.addMember(umsMember);
    }

    @RequestMapping("getAllMember")
    public List<UmsMember> getAllUser() {
        List<UmsMember> umsMember = userService.getAllUser();
        return umsMember;
    }
}
