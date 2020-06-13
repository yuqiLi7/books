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
        List<JsonNode> nodes = lendInfoRepository.findByReader(readerRepository.findById(id).get()).stream().map(info -> {
            ObjectNode node = mapper.createObjectNode();
            node.put("id", info.getId());
            node.put("key", info.getId());
            node.put("name", info.getBook().getName());
            node.put("reader", info.getReader().getName());
            node.put("lendDate", info.getLendDate().toLocalDate().toString());
            node.put("backDate", info.getBackDate().toLocalDate().toString());
            return node;
        }).collect(Collectors.toList());
        return nodes;
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

    @GetMapping("/rentBook")
    public String rentBook(@RequestParam Long readerId, @RequestParam Long bookId) {
        LendInfo info = new LendInfo();
        info.setBook(bookRepository.findById(bookId).get());
        info.setReader(readerRepository.findById(readerId).get());
        info.setLendDate(LocalDateTime.now());
        lendInfoRepository.save(info);
        return "{code: \"200\"}";
    }
}
