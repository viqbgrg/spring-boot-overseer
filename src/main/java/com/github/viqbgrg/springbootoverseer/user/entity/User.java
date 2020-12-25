package com.github.viqbgrg.springbootoverseer.user.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author bing
 * @since 2020-12-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;

    private String email;

    private String password;

    private Boolean locked;


}
