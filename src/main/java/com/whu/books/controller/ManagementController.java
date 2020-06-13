package com.whu.books.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.whu.books.models.Reader;
import com.whu.books.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @class ManagementController
 * @description 
 * @author 奥力给
 * @date 2020.06.13 21:23
 */

@RestController
@RequestMapping("/")
public class ManagementController {

    @Autowired
    ReaderRepository readerRepository;

    /**
     *  查询所有的读者信息
     * @return
     */
    @GetMapping("/readers")
    public List<JsonNode> readers(){
        ObjectMapper mapper = new ObjectMapper();
        List<JsonNode> nodes = readerRepository.findAll().stream().map(reader -> {
            ObjectNode node = mapper.createObjectNode();
            node.put("id", reader.getId());
            node.put("key", reader.getId());
            node.put("name",reader.getName());
            node.put("sex", reader.getSex());
            node.put("birth", reader.getBirth().toString());
            node.put("address", reader.getAddress());
            node.put("phone", reader.getPhone());
            return node;
        }).collect(Collectors.toList());
        return nodes;
    }
}
