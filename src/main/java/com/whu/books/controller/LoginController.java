package com.whu.books.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.whu.books.repository.AdminRepository;
import com.whu.books.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 奥力给
 * @class LoginController
 * @description
 * @date 2020.06.11 22:19
 */

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    ReaderRepository readerRepository;

    @PostMapping("")
    public String login(@RequestBody JsonNode node) {
        return "用户名: " + node.get("username") + " 密码: " + node.get("password");
    }
}
