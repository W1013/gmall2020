package com.whg.gmall.user.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.whg.gmall.bean.UmsMember;
import com.whg.gmall.bean.UmsMemberReceiveAddress;
import com.whg.gmall.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Reference;

/**
 * Created by Administrator on 2020/9/26.
 */
@Controller
public class UserController {
   @Reference
    private UserService userService;

    @RequestMapping("index")
    public String index() {
        return "hello user";
    }

    @RequestMapping("getReceiveAddressByMemberId")
    @ResponseBody
    public List<UmsMemberReceiveAddress> getReceiveAddressByMemberId(String memberId) {
        List<UmsMemberReceiveAddress> umsMemberReceiveAddressList = userService.getReceiveAddressByMemberId(memberId);
        return umsMemberReceiveAddressList;
    }

    @RequestMapping("addMember")
    public void addMember(@RequestBody UmsMember umsMember) {
        userService.addMember(umsMember);
    }

    @RequestMapping("getAllMember")
    @ResponseBody
    public List<UmsMember> getAllUser() {
        List<UmsMember> umsMember = userService.getAllUser();
        return umsMember;
    }
}
