package com.qf.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.IEmailService;
import com.qf.IRegistService;
import com.qf.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author：H
 * @date：2019/6/29
 */
@Controller
@RequestMapping("user")
public class RegistController {
    @Reference
    IRegistService registService;

    @RequestMapping("setName")
    @ResponseBody
    public Object setName(@RequestParam String name){
        int i = registService.setName(name);
        return i;
    }
    @RequestMapping("toRegist")
    public String toRegist(){
        return "regist";
    }

    @RequestMapping("sendEmail")
    public Object setEmail(@RequestParam String emailName, HttpSession session){
        System.out.println(emailName);
        int num = registService.sendTestEmail(emailName);
        session.setAttribute("code",num);
        return null;
    }
    @RequestMapping("testCode")
    @ResponseBody
    public int testCode(@RequestParam String code,HttpSession session){
        String codeNum = (String) session.getAttribute("code");
        if(code.equals(codeNum)){
            return 1;
        }
        return 0;
    }
    @RequestMapping("add")
    public Object add(User user){
        registService.add(user);
        return "index";
    }
}

