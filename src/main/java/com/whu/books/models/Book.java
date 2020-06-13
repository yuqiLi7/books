package com.whu.books.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    private String publish;
    @Column(unique = true, nullable = false)
    private String isbn;
    private String introduction;
    private String language;
    private BigDecimal price;
    private LocalDateTime publishDate;

    @OneToOne(mappedBy = "book", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties({"book"})
    private LendInfo lendInfo;

    @OneToOne
    private Classify classify;

    private int pressmark;
    private int state;

}
