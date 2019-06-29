package com.qf;

import com.qf.entity.User;

/**
 * @author：H
 * @date：2019/6/29
 */
public interface ILoginService {

    void login(User user);

    void findEmail(String name);

    void updatepwd(String name, String password);
}
