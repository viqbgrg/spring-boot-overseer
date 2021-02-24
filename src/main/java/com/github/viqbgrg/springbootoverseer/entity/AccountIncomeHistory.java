package com.github.viqbgrg.springbootoverseer.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author bing
 * @since 2021-02-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AccountIncomeHistory implements Serializable {


    /**
     * 账号id
     */
    @TableId(value = "user_i_d", type = IdType.INPUT)
    private Long userID;

    /**
     * 日期
     */
    private LocalDate day;

    private List<PdcDetail> pdcDetail;

    /**
     * 更新时间
     */
    private LocalDateTime updateAt;


}
