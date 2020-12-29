package com.github.viqbgrg.springbootoverseer.user.controller;


import com.github.viqbgrg.springbootoverseer.domain.dto.UserLoginDto;
import com.github.viqbgrg.springbootoverseer.domain.dto.UserSignInDto;
import com.github.viqbgrg.springbootoverseer.user.entity.User;
import com.github.viqbgrg.springbootoverseer.user.service.IUserService;
import com.github.viqbgrg.springbootoverseer.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

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
@Slf4j
public class UserController {
    private IUserService userService;
//    private Subject


    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signIn")
    public ResponseEntity<Void> signIn(@Validated @RequestBody UserSignInDto user) {
        User users = new User();
        BeanUtils.copyProperties(user, users);
        return userService.signIn(users) ? ResponseEntity.status(HttpStatus.CREATED).build() : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@Validated @RequestBody UserLoginDto userLoginDto, HttpServletResponse response) {
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userLoginDto.getUsername(), userLoginDto.getPassword());
        Subject subject = SecurityUtils.getSubject();
        subject.login(usernamePasswordToken);
        response.addHeader("authorization", JwtUtils.sign(userLoginDto.getUsername(), userLoginDto.getPassword()));
        return ResponseEntity.ok().build();
    }

}
