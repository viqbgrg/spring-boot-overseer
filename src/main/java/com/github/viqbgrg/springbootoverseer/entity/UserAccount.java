package com.github.viqbgrg.springbootoverseer.entity;

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
 * @since 2020-12-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserAccount implements Serializable {


    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户id
     */
    private Long accountId;

    /**
     * 用户创建时间
     */
    private LocalDateTime createAt;

    /**
     * 更新时间
     */
    private LocalDateTime updateAt;


}
