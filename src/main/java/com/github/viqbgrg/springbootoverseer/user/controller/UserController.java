package com.github.viqbgrg.springbootoverseer.user.controller;


import com.github.viqbgrg.springbootoverseer.domain.dto.UserSignInDto;
import com.github.viqbgrg.springbootoverseer.user.entity.User;
import com.github.viqbgrg.springbootoverseer.user.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author bing
 * @since 2020-12-25
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signIn")
    public ResponseEntity<Void> signIn(@Validated @RequestBody UserSignInDto user) {
        User users = new User();
        BeanUtils.copyProperties(user, users);
        return userService.signIn(users) ? ResponseEntity.status(HttpStatus.CREATED).build() : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
