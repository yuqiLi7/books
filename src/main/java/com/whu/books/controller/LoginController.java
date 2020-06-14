package com.whu.books.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.whu.books.models.Admin;
import com.whu.books.models.Reader;
import com.whu.books.repository.AdminRepository;
import com.whu.books.repository.ReaderRepository;
import com.whu.books.util.MD5Utils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
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

    /**
     *  处理登录逻辑
     * @param node
     * @return
     */
    @PostMapping("")
    public Res login(@RequestBody JsonNode node) {
        String type = node.get("type").asText();
        if (type.equals("reader")) {
            // 读者登录
            String phone = node.get("phone").asText();
            String password = node.get("password").asText();
            Reader reader = readerRepository.findByPhoneAndPassword(phone, MD5Utils.code(password));
            if (reader != null) {
                return new Res(type, "ok", type, reader.getId(), reader.getName());
            } else {
                return new Res("error");
            }
        } else if (type.equals("admin")) {
            // 管理员登录
            String phone = node.get("phone").asText();
            String password = node.get("password").asText();
            Admin admin = adminRepository.findByPhoneAndPassword(phone, MD5Utils.code(password));
            if (admin != null) {
                return new Res(type, "ok", type, admin.getId(), "管理员");
            } else {
                return new Res("error");
            }
        } else {
            return new Res("error");
        }
    }

    @AllArgsConstructor
    @Getter
    @Setter
    class Res {
        String currentAuthority;
        String status;
        String type;
        Long id;
        String name;

        public Res(String currentAuthority) {
            this.currentAuthority = currentAuthority;
        }
    }
}
