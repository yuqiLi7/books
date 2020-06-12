package com.whu.books.service;

import com.whu.books.models.Reader;

/**
 * @author : yuqi Li
 * @date : 2020/6/12
 */
public interface LoginService {
    Reader checkReader(String phone, String password);
}
