package com.github.viqbgrg.springbootoverseer.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2021-01-21
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
     * 登陆账号
     */
    private String username;

    /**
     * 登陆密码
     */
    private String password;

    /**
     * 账号id
     */
    private String userID;

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
