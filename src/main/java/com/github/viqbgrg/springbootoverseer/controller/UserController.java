package com.github.viqbgrg.springbootoverseer.controller;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.viqbgrg.springbootoverseer.domain.dto.UserLoginDto;
import com.github.viqbgrg.springbootoverseer.domain.dto.UserSignInDto;
import com.github.viqbgrg.springbootoverseer.domain.vo.CurrentUserVo;
import com.github.viqbgrg.springbootoverseer.entity.User;
import com.github.viqbgrg.springbootoverseer.service.IUserService;
import com.github.viqbgrg.springbootoverseer.utils.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.github.viqbgrg.springbootoverseer.shiro.JwtAuthFilter.AUTHORIZATION_HEADER;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author bing
 * @since 2020-12-25
 */
@Api(value = "登陆", tags = "login")
@RestController
@RequestMapping("/login")
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

    @PostMapping("/account")
    @ApiOperation(value = "login", nickname = "login")
    public ResponseEntity<JWTToken> login(@Validated @ApiParam(name = "账号密码", required = true) @RequestBody UserLoginDto userLoginDto) {
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userLoginDto.getUsername(), userLoginDto.getPassword());
        Subject subject = SecurityUtils.getSubject();
        subject.login(usernamePasswordToken);
        HttpHeaders httpHeaders = new HttpHeaders();
        String jwt = JwtUtils.sign(userLoginDto.getUsername(), userLoginDto.getUsername());
        httpHeaders.add(AUTHORIZATION_HEADER, "Bearer " + jwt);
        return new ResponseEntity<>(new JWTToken(jwt), httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/outLogin")
    @ApiOperation(value = "用户退出", nickname = "outLogin")
    public ResponseEntity<Void> outLogin() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.getPrincipals() != null) {
            log.info("为空");
        }
        SecurityUtils.getSubject().logout();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/currentUser")
    @ApiOperation(value = "用户信息", nickname = "currentUser", tags = "api")
    public ResponseEntity<CurrentUserVo> currentUser() {
        CurrentUserVo currentUserVo = new CurrentUserVo();
        Subject subject = SecurityUtils.getSubject();
        User User = (User) subject.getPrincipal();
        BeanUtils.copyProperties(User, currentUserVo);
        return ResponseEntity.ok(currentUserVo);
    }


    /**
     * Object to return as body in JWT Authentication.
     */
    static class JWTToken {

        private String idToken;

        JWTToken(String idToken) {
            this.idToken = idToken;
        }

        @JsonProperty("id_token")
        String getIdToken() {
            return idToken;
        }

        void setIdToken(String idToken) {
            this.idToken = idToken;
        }
    }

}
