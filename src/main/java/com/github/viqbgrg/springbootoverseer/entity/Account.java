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
 * @since 2021-01-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Account implements Serializable {


    /**
     * 登陆密码
     */
    private String password;

    /**
     * 账号id
     */
    @TableId(value = "user_i_d", type = IdType.INPUT)
    private Long userID;

    /**
     * 登陆账号
     */
    private String userName;

    /**
     * 账号昵称
     */
    private String nickName;

    /**
     * 再次登陆的key
     */
    private String loginKey;

    /**
     * 再次登陆的key
     */
    private String sessionID;

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
