package com.github.viqbgrg.springbootoverseer.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.viqbgrg.springbootoverseer.entity.AccountHistory;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author bing
 * @since 2021-01-27
 */
@Mapper
@Repository
public interface AccountHistoryMapper extends BaseMapper<AccountHistory> {

}
