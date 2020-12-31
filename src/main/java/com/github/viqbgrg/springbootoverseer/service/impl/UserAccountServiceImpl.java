package com.github.viqbgrg.springbootoverseer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.viqbgrg.springbootoverseer.entity.UserAccount;
import com.github.viqbgrg.springbootoverseer.mapper.UserAccountMapper;
import com.github.viqbgrg.springbootoverseer.service.IUserAccountService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bing
 * @since 2020-12-31
 */
@Service
public class UserAccountServiceImpl extends ServiceImpl<UserAccountMapper, UserAccount> implements IUserAccountService {

}
