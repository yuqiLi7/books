package com.whu.books.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@NoArgsConstructor
@Entity
public class LendInfo{

    @Id
    private long id;
    @OneToOne
    private Book book;
    @ManyToOne
    @JsonIgnoreProperties({"lendInfo"})
    private Reader reader;
    private LocalDateTime lendDate;

    private LocalDateTime backDate;
}
