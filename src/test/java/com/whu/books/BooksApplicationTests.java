package com.whu.books;

import com.whu.books.util.MD5Utils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class BooksApplicationTests {

    /**
     * 测试MD5加密密码功能
     */
    @Test
    public void testMD5(){
        String password = "123243546342";
        System.out.println("password = " + password);
        String encoded = MD5Utils.code(password);
        System.out.println("encoded = " + encoded);
        assert encoded.equals("087adceb109a6642c417d79fde3a78f5");
    }
}
