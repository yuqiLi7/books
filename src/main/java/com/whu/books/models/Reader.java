package com.whu.books.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @class Reader
 * @description 
 * @author 奥力给
 * @date 2020.06.11 19:13
 */

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "reader", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"reader","book"})
    private List<LendInfo> lendInfo;

    private String name;
    private String sex;
    private LocalDateTime birth;
    private String address;
}
