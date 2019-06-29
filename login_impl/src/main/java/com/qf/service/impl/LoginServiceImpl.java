package com.qf.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qf.IEmailService;
import com.qf.ILoginService;
import com.qf.dao.LoginMapper;
import com.qf.entity.Email;
import com.qf.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author：H
 * @date：2019/6/29
 */
@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    LoginMapper loginMapper;
    @Reference
    IEmailService emailService;
    @Override
    public void login(User user) {
        loginMapper.selectOne(new QueryWrapper<User>().eq(user.getName(),user.getPassword()));
    }

    @Override
    public void findEmail(String name) {
        User user = loginMapper.selectOne(new QueryWrapper<User>().eq("name", name));
        Email email=new Email();
        email.setEmailName(user.getEmail());
        email.setSubject("找回密码");
        email.setContent("请点击<a href='http://localhost:8080/userLogin/resetpwd?name="+name+"'>这里</a>找回密码~");
        emailService.sendEmail(email);
    }

    @Override
    public void updatepwd(String name, String password) {
        User user=new User();
        user.setName(name);
        user.setPassword(password);
        loginMapper.update(user,new QueryWrapper<User>().eq("name",name));
    }
}

