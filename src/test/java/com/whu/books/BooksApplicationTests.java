package com.whu.books;

import com.whu.books.util.MD5Utils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.time.LocalDate;

@SpringBootTest
class BooksApplicationTests {

    /**
     * 测试MD5加密密码功能
     */
    @Test
    public void testMD5(){
        LocalDate date = LocalDate.parse("2020-05-14");
        System.out.println("date = " + date.atStartOfDay());
    }
}
