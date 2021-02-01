package com.github.viqbgrg.springbootoverseer.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author bing
 * @since 2021-02-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AccountHistory implements Serializable {


    /**
     * 账号id
     */
    @TableId(value = "user_i_d", type = IdType.INPUT)
    private Long userID;

    /**
     * 日期
     */
    @TableId(value = "day", type = IdType.INPUT)
    private LocalDate day;

    private Integer pdc;

    private Integer boxPdc;

    /**
     * 速度
     */
    private Integer lastSpeed;

    /**
     * 速度
     */
    private Integer deploySpeed;

    private Integer balance;

    private Integer income;

    private Integer refreshes;

    private Integer[] speedStat;

    private Integer[] pdcDetail;

    private Integer[] produceStat;

    /**
     * 更新时间
     */
    private LocalDateTime updateAt;


}
