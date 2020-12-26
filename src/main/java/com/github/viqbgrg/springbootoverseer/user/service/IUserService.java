package com.github.viqbgrg.springbootoverseer.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.viqbgrg.springbootoverseer.user.entity.User;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author bing
 * @since 2020-12-25
 */
public interface IUserService extends IService<User> {

    User findUserByUsername(String username);

    Object signIn(User users1);
}
