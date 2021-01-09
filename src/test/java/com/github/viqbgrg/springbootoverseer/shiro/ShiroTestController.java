package com.github.viqbgrg.springbootoverseer.shiro;

import com.github.viqbgrg.springbootoverseer.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class ShiroTestController {
    @GetMapping(value = "/shiroSubject")
    public ResponseEntity<User> shiroSubject() {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        return ResponseEntity.ok(user);
    }

    @GetMapping(value = "/text")
    public String test() {
        return "test";
    }
}
