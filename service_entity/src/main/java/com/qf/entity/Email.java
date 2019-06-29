package com.qf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author：H
 * @date：2019/6/29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Email implements Serializable {
    //收件人
    String emailName;
    //发件人
    String sender="ponymei@sina.com";
    //内容
    String content;
    //标题
    String subject;

}

