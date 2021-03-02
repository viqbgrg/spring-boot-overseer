package com.github.viqbgrg.springbootoverseer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.viqbgrg.springbootoverseer.entity.Account;
import com.github.viqbgrg.springbootoverseer.entity.UserAccount;
import com.github.viqbgrg.springbootoverseer.mapper.UserAccountMapper;
import com.github.viqbgrg.springbootoverseer.service.IUserAccountService;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<UserAccount> getAllAccount(Long userId) {
        List<UserAccount> userAccounts = this.baseMapper.selectList(new QueryWrapper<UserAccount>().eq("user_id", userId));
        return userAccounts;
    }
}
