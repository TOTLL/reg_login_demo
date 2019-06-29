package com.qf;

import com.qf.entity.User;

/**
 * @author：H
 * @date：2019/6/29
 */
public interface IRegistService {
    /**
     *
     * @param name
     * @return
     */
    int setName(String name);

    void add(User user);

    int sendTestEmail(String emailName);
}
