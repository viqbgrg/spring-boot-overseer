package com.github.viqbgrg.springbootoverseer.controller;


import com.github.viqbgrg.springbootoverseer.domain.dto.XunleiAccountDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author bing
 * @since 2020-12-31
 */
@Controller
@RequestMapping("/account")
public class AccountController {

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody XunleiAccountDto xunleiAccount) {

        return null;
    }
}
