package com.github.viqbgrg.springbootoverseer.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.github.viqbgrg.springbootoverseer.xunlei.zqb.entity.MineInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author bing
 * @since 2021-01-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AccountData implements Serializable {


    /**
     * 账号主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 账号信息
     */
    private MineInfo mineInfo;

    /**
     * 更新时间
     */
    private LocalDateTime updateAt;


}
