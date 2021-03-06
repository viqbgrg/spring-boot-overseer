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
    private LocalDate day;

    private int pdc;

    private int boxPdc;

    /**
     * 速度
     */
    private int lastSpeed;

    /**
     * 速度
     */
    private int deploySpeed;

    private int balance;

    private int income;

    private int refreshes;

    private List<SpeedStat> speedStat;

    private List<PdcDetail> pdcDetail;

    private List<ProduceStatHistory> produceStat;

    /**
     * 更新时间
     */
    private LocalDateTime updateAt;


}
