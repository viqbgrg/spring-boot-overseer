package com.github.viqbgrg.springbootoverseer.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author bing
 * @since 2021-01-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Account implements Serializable {


    /**
     * 账号主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 账号id
     */
    private Long userId;

    /**
     * 账号昵称
     */
    private String nickName;

    /**
     * 再次登陆的key
     */
    private String loginKey;

    private String creditkey;

    private String secureKey;

    /**
     * 用户创建时间
     */
    private LocalDateTime createAt;

    /**
     * 更新时间
     */
    private LocalDateTime updateAt;


}
