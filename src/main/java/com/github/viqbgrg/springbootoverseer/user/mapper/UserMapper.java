package com.github.viqbgrg.springbootoverseer.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.viqbgrg.springbootoverseer.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author bing
 * @since 2020-12-25
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
