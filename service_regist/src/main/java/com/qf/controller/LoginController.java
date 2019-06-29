package com.qf.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.ILoginService;
import com.qf.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * @author：H
 * @date：2019/6/29
 */
@Controller
@RequestMapping("userLogin")
public class LoginController {

    @Reference
    ILoginService loginService;
    @RequestMapping("toLogin")
    public String tologin(){
        return "login";
    }
    @RequestMapping("login")
    public String login(User user){
        loginService.login(user);
        return "ok";
    }
    @RequestMapping("findpwd")
    public String findpwd(){
        return "find";
    }
    @RequestMapping("findEmail")
    public void findEmail(@RequestParam String name){
        loginService.findEmail(name);
    }
    @RequestMapping("resetpwd")
    public String resetpwd(@RequestParam String name, Model model){
        model.addAttribute("name",name);
        return "reset";
    }
    @RequestMapping("updatepwd")
    public String updatepwd(@RequestParam String name,@RequestParam String password){
        loginService.updatepwd(name,password);
        return "ok";
    }
}

