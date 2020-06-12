package com.whu.books.service;

import com.whu.books.models.Reader;
import com.whu.books.repository.ReaderRepository;
import com.whu.books.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : yuqi Li
 * @date : 2020/6/12
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private ReaderRepository readerRepository;

    @Override
    public Reader checkReader(String phone, String password) {
        Reader user = readerRepository.findByIDAndPassword(phone, MD5Utils.code(password));
        return user;
    }
}
