package com.github.viqbgrg.springbootoverseer.mapper;

import com.github.viqbgrg.springbootoverseer.entity.AccountIncomeHistory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


/**
* <p>
*  Mapper 接口
* </p>
*
* @author bing
* @since 2021-02-20
*/
@Mapper
@Repository
public interface AccountIncomeHistoryMapper extends BaseMapper<AccountIncomeHistory> {

}
