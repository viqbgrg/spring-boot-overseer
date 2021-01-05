package com.github.viqbgrg.springbootoverseer.controller;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.viqbgrg.springbootoverseer.domain.dto.UserLoginDto;
import com.github.viqbgrg.springbootoverseer.domain.dto.UserSignInDto;
import com.github.viqbgrg.springbootoverseer.domain.vo.UserInfoVo;
import com.github.viqbgrg.springbootoverseer.entity.User;
import com.github.viqbgrg.springbootoverseer.service.IUserService;
import com.github.viqbgrg.springbootoverseer.utils.JwtUtils;
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
    public ResponseEntity<JWTToken> login(@Validated @RequestBody UserLoginDto userLoginDto) {
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userLoginDto.getUsername(), userLoginDto.getPassword());
        Subject subject = SecurityUtils.getSubject();
        subject.login(usernamePasswordToken);
        HttpHeaders httpHeaders = new HttpHeaders();
        String jwt =JwtUtils.sign(userLoginDto.getUsername(), userLoginDto.getPassword());
        httpHeaders.add("authorization", "Bearer " + jwt);
        return new ResponseEntity<>(new JWTToken(jwt), httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity<Void> logout() {
        Subject subject = SecurityUtils.getSubject();
        if(subject.getPrincipals() != null) {
            log.info("为空");
        }
        SecurityUtils.getSubject().logout();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/userinfo")
    public ResponseEntity<UserInfoVo> userinfo() {
        UserInfoVo userInfoVo = new UserInfoVo();
        Subject subject = SecurityUtils.getSubject();
        String principal = (String)subject.getPrincipal();
        User userByUsername = userService.findUserByUsername(principal);
        BeanUtils.copyProperties(userByUsername, userInfoVo);
        return ResponseEntity.ok(userInfoVo);
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
