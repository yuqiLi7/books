package com.whu.books.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.whu.books.models.Book;
import com.whu.books.models.Reader;
import com.whu.books.repository.BookRepository;
import com.whu.books.repository.ClassifyRepository;
import com.whu.books.repository.LendInfoRepository;
import com.whu.books.repository.ReaderRepository;
import com.whu.books.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @Autowired
    ClassifyRepository classifyRepository;

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
    public List<JsonNode> rents() {
        ObjectMapper mapper = new ObjectMapper();
        List<JsonNode> nodes = lendInfoRepository.findAll().stream().map(rent -> {
            ObjectNode node = mapper.createObjectNode();
            node.put("key", rent.getId());
            node.put("id", rent.getId());
            node.put("name", rent.getBook().getName());
            node.put("reader", rent.getReader().getName());
            node.put("lendDate", rent.getLendDate().toLocalDate().toString());
            node.put("backDate", rent.getBackDate().toLocalDate().toString());
            return node;
        }).collect(Collectors.toList());
        return nodes;
    }

    /**
     * 添加读者
     *
     * @param node
     * @return
     */
    @PostMapping("/reader")
    public String addReader(@RequestBody JsonNode node) {
        JsonNode data = node.get("reader");
        LocalDateTime time = LocalDate.parse(data.get("birth").asText().substring(0, 10)).atStartOfDay();
        Reader reader = new Reader();
        reader.setBirth(time);
        reader.setName(data.get("name").asText());
        reader.setAddress(data.get("address").asText());
        reader.setPhone(data.get("phone").asText());
        reader.setSex(data.get("sex").asText());
        reader.setPassword(MD5Utils.code("123456"));
        readerRepository.save(reader);
        return "{code: \"200\"}";
    }

    /**
     * 修改用户信息
     *
     * @param node
     * @return
     */
    @PutMapping("/reader")
    public String updateReader(@RequestBody JsonNode node) {
        JsonNode data = node.get("reader");
        Reader reader = readerRepository.findById(data.get("id").asLong()).get();
        LocalDateTime time = LocalDate.parse(data.get("birth").asText().substring(0, 10)).atStartOfDay();
        reader.setBirth(time);
        reader.setName(data.get("name").asText());
        reader.setAddress(data.get("address").asText());
        reader.setPhone(data.get("phone").asText());
        reader.setSex(data.get("sex").asText());
        System.out.println("reader = " + reader);
        readerRepository.save(reader);
        return "{code: \"200\"}";
    }

    /**
     *  删除一个读者
     * @param id
     * @return
     */
    @DeleteMapping("/reader")
    public String deleteReader(@RequestParam Long id) {
        readerRepository.deleteById(id);
        return "{code: \"200\"}";
    }

    /**
     * 添加一本书
     * @param node
     * @return
     */
    @PostMapping("/book")
    public String addBook(@RequestBody JsonNode node){
        JsonNode data = node.get("book");
        Book book = new Book();
        book.setIsbn(data.get("isbn").asText());
        book.setName(data.get("name").asText());
        book.setAuthor(data.get("author").asText());
        book.setLanguage(data.get("language").asText());
        book.setClassify(classifyRepository.findByName(data.get("classify").asText()));
        book.setPrice(BigDecimal.valueOf(Double.parseDouble(data.get("price").asText())));
        book.setPublish(data.get("publish").asText());
        bookRepository.save(book);
        return "{code: \"200\"}";
    }

    @PutMapping("/book")
    public String updateBook(@RequestBody JsonNode node){
        JsonNode data = node.get("book");
        Book book = bookRepository.findById(Long.valueOf(data.get("id").asText())).get();
        book.setIsbn(data.get("isbn").asText());
        book.setName(data.get("name").asText());
        book.setAuthor(data.get("author").asText());
        book.setLanguage(data.get("language").asText());
        book.setClassify(classifyRepository.findByName(data.get("classify").asText()));
        book.setPrice(BigDecimal.valueOf(Double.parseDouble(data.get("price").asText())));
        book.setPublish(data.get("publish").asText());
        bookRepository.save(book);
        return "{code: \"200\"}";
    }

    @DeleteMapping("/book")
    public String deleteBook(@RequestParam Long id){
        bookRepository.deleteById(id);
        return "{code: \"200\"}";
    }

}
