package com.qf.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qf.IEmailService;
import com.qf.IRegistService;
import com.qf.dao.RegistMapper;
import com.qf.entity.Email;
import com.qf.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author：H
 * @date：2019/6/29
 */
@Service
public class RegistServiceImpl implements IRegistService {
    @Autowired
    RegistMapper registMapper;
    @Reference
    IEmailService emailService;
    @Override
    public int setName(String name) {
        List<User> list = registMapper.selectList(new QueryWrapper<User>().eq("name", name));
        if(list.isEmpty()){
            return 1;
        }
        return 0;
    }

    @Override
    public void add(User user) {
        registMapper.insert(user);
    }

    @Override
    public int sendTestEmail(String emailName) {
        Email email=new Email();
        email.setEmailName(emailName);
        //产生1000-9999的随机数
        Integer num=(int)(Math.random()*(9999-1000+1))+1000;
        email.setContent("验证码为："+num);
        email.setSubject("注册验证");
        //调用发送方法
        emailService.sendEmail(email);
        return  num;
    }
}

