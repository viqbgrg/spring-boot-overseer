package com.github.viqbgrg.springbootoverseer.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.viqbgrg.springbootoverseer.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author bing
 * @since 2020-12-25
 */
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {

}
