package com.github.viqbgrg.springbootoverseer.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.viqbgrg.springbootoverseer.entity.UserAccount;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


/**
* <p>
*  Mapper 接口
* </p>
*
* @author bing
* @since 2020-12-31
*/
@Mapper
@Repository
public interface UserAccountMapper extends BaseMapper<UserAccount> {

}
