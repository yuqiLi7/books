package com.whu.books.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.whu.books.models.Book;
import com.whu.books.models.LendInfo;
import com.whu.books.models.Reader;
import com.whu.books.repository.BookRepository;
import com.whu.books.repository.LendInfoRepository;
import com.whu.books.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 奥力给
 * @class ReaderController
 * @description
 * @date 2020.06.13 21:23
 */

@RestController
@RequestMapping("/")
public class ReaderController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    ReaderRepository readerRepository;

    @Autowired
    LendInfoRepository lendInfoRepository;

    /**
     * 按书名关键字搜索书籍
     *
     * @param name
     * @return
     */
    @GetMapping("/searchBook")
    public List<JsonNode> searchBook(@RequestParam String name) {
        List<Book> books = bookRepository.findByNameContaining(name);
        if (books.isEmpty()) {
            return new ArrayList<>();
        } else {
            ObjectMapper mapper = new ObjectMapper();
            List<JsonNode> nodes = books.stream().map(book -> {
                ObjectNode node = mapper.createObjectNode();
                node.put("key", book.getId());
                node.put("id", book.getId());
                node.put("isbn", book.getIsbn());
                node.put("name", book.getName());
                node.put("author", book.getAuthor());
                node.put("language", book.getLanguage());
                node.put("classify", book.getClassify().getName());
                node.put("price", book.getPrice());
                node.put("publish", book.getPublish());
                return node;
            }).collect(Collectors.toList());
            return nodes;
        }
    }

    /**
     * 搜索当前用户的借阅记录
     *
     * @param id
     * @return
     */
    @GetMapping("/searchRent")
    public List<JsonNode> searchRent(@RequestParam Long id) {
        ObjectMapper mapper = new ObjectMapper();
        List<LendInfo> lendInfo = lendInfoRepository.findByReader(readerRepository.findById(id).get());
        if (lendInfo == null) {
            return new ArrayList<>();
        } else {
            List<JsonNode> nodes = lendInfo.stream().map(info -> {
                ObjectNode node = mapper.createObjectNode();
                node.put("id", info.getId());
                node.put("key", info.getId());
                node.put("name", info.getBook().getName());
                node.put("reader", info.getReader().getName());
                node.put("lendDate", info.getLendDate().toLocalDate().toString());
                if (info.getBackDate() != null) {
                    node.put("backDate", info.getBackDate().toLocalDate().toString());
                }
                return node;
            }).collect(Collectors.toList());
            return nodes;
        }
    }

    /**
     * 获取当前用户的信息
     *
     * @param id
     * @return
     */
    @GetMapping("/readerInfo")
    public JsonNode readerInfo(@RequestParam Long id) {
        Reader reader = readerRepository.findById(id).get();
        ObjectNode node = new ObjectMapper().createObjectNode();
        node.put("id", reader.getId());
        node.put("name", reader.getName());
        node.put("sex", reader.getSex());
        node.put("phone", reader.getPhone());
        node.put("birth", reader.getBirth().toLocalDate().toString());
        node.put("address", reader.getAddress());
        return node;
    }

    /**
     * 搜索读者借阅的图书
     *
     * @param readerId
     * @param bookId
     * @return
     */
    @GetMapping("/rentBook")
    public String rentBook(@RequestParam Long readerId, @RequestParam Long bookId) {
        LendInfo info = new LendInfo();
        info.setBook(bookRepository.findById(bookId).get());
        info.setReader(readerRepository.findById(readerId).get());
        info.setLendDate(LocalDateTime.now());
        lendInfoRepository.save(info);
        return "{code: \"200\"}";
    }

    @GetMapping("/currentUser")
    public String currentUser() {
        return "{\n" +
                "  \"name\": \"User\",\n" +
                "  \"avatar\": \"https://gw.alipayobjects.com/zos/antfincdn/XAosXuNZyF/BiazfanxmamNRoxxVxka.png\",\n" +
                "  \"userid\": \"00000001\",\n" +
                "  \"email\": \"antdesign@alipay.com\",\n" +
                "  \"signature\": \"海纳百川，有容乃大\",\n" +
                "  \"title\": \"交互专家\",\n" +
                "  \"group\": \"蚂蚁金服－某某某事业群－某某平台部－某某技术部－UED\",\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"key\": \"0\",\n" +
                "      \"label\": \"很有想法的\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"key\": \"1\",\n" +
                "      \"label\": \"专注设计\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"key\": \"2\",\n" +
                "      \"label\": \"辣~\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"key\": \"3\",\n" +
                "      \"label\": \"大长腿\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"key\": \"4\",\n" +
                "      \"label\": \"川妹子\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"key\": \"5\",\n" +
                "      \"label\": \"海纳百川\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"notifyCount\": 12,\n" +
                "  \"unreadCount\": 11,\n" +
                "  \"country\": \"China\",\n" +
                "  \"geographic\": {\n" +
                "    \"province\": {\n" +
                "      \"label\": \"浙江省\",\n" +
                "      \"key\": \"330000\"\n" +
                "    },\n" +
                "    \"city\": {\n" +
                "      \"label\": \"杭州市\",\n" +
                "      \"key\": \"330100\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"address\": \"西湖区工专路 77 号\",\n" +
                "  \"phone\": \"0752-268888888\"\n" +
                "}\n";
    }
}
