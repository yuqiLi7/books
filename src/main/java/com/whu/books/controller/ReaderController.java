package com.whu.books.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.whu.books.models.Book;
import com.whu.books.models.Reader;
import com.whu.books.repository.BookRepository;
import com.whu.books.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
     *  获取当前用户的信息
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
     *  修改用户信息
     * @param node
     * @return
     */
    @PutMapping("/readerInfo")
    public String readerInfo(@RequestBody JsonNode node){
        JsonNode data = node.get("reader");
        Reader reader = readerRepository.findById(data.get("id").asLong()).get();
        LocalDateTime time = LocalDate.parse(data.get("birth").asText().substring(0,10)).atStartOfDay();
        reader.setBirth(time);
        reader.setName(data.get("name").asText());
        reader.setAddress(data.get("address").asText());
        reader.setPhone(data.get("phone").asText());
        reader.setSex(data.get("sex").asText());
        Reader reader1 = readerRepository.save(reader);
        System.out.println("reader1 = " + reader1);
        return "200";
    }
}
