package com.whu.books.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.whu.books.repository.BookRepository;
import com.whu.books.repository.LendInfoRepository;
import com.whu.books.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 奥力给
 * @class ManagementController
 * @description
 * @date 2020.06.13 21:23
 */

@RestController
@RequestMapping("/")
public class ManagementController {

    @Autowired
    ReaderRepository readerRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    LendInfoRepository lendInfoRepository;

    /**
     * 查询所有的读者信息
     *
     * @return
     */
    @GetMapping("/readers")
    public List<JsonNode> readers() {
        ObjectMapper mapper = new ObjectMapper();
        List<JsonNode> nodes = readerRepository.findAll().stream().map(reader -> {
            ObjectNode node = mapper.createObjectNode();
            node.put("id", reader.getId());
            node.put("key", reader.getId());
            node.put("name", reader.getName());
            node.put("sex", reader.getSex());
            node.put("birth", reader.getBirth().toLocalDate().toString());
            node.put("address", reader.getAddress());
            node.put("phone", reader.getPhone());
            return node;
        }).collect(Collectors.toList());
        return nodes;
    }

    /**
     * 查询所有图书
     *
     * @return
     */
    @GetMapping("/books")
    public List<JsonNode> books() {
        ObjectMapper mapper = new ObjectMapper();
        List<JsonNode> nodes = bookRepository.findAll().stream().map(book -> {
            ObjectNode node = mapper.createObjectNode();
            node.put("id", book.getId());
            node.put("key", book.getId());
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

    /**
     * 查询所有借阅信息
     *
     * @param
     * @return
     */
    @GetMapping("/rents")
    public List<JsonNode> rents(){
        ObjectMapper mapper = new ObjectMapper();
        List<JsonNode> nodes = lendInfoRepository.findAll().stream().map(rent->{
            ObjectNode node = mapper.createObjectNode();
            node.put("key", rent.getId());
            node.put("id", rent.getId());
            node.put("name" , rent.getBook().getName());
            node.put("reader", rent.getReader().getName());
            node.put("lendDate", rent.getLendDate().toLocalDate().toString());
            node.put("backDate", rent.getBackDate().toLocalDate().toString());
            return node;
        }).collect(Collectors.toList());
        return nodes;
    }



    @PostMapping("/addReader")
    public String addReader(@RequestBody JsonNode node) {
        System.out.println("phone: " + node.get("phone"));
        System.out.println("password: " + node.get("password"));
        return "phone: " + node.get("phone") + "password: " + node.get("password");
    }
}
