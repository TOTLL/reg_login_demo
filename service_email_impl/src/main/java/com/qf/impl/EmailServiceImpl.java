package com.qf.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.qf.IEmailService;
import com.qf.entity.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.util.Date;

/**
 * @author：H
 * @date：2019/6/29
 */
@Service
public class EmailServiceImpl implements IEmailService {
    @Autowired
    JavaMailSender javaMailSender;

    @Override
    public void sendEmail(Email email) {
        //创建一封邮件
        MimeMessage mimeMessage =  javaMailSender.createMimeMessage();
        //创建一个Spring提供的邮件帮助对象
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            //设置发送方
            messageHelper.setFrom(email.getSender());
            //设置收件方
            messageHelper.addTo(email.getEmailName());
            //设置标题
            messageHelper.setSubject(email.getSubject());
            //设置内容
            messageHelper.setText(email.getContent(),true);
            //设置发送时间
            messageHelper.setSentDate(new Date());
            //发送邮件
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

