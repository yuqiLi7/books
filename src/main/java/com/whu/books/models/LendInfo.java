package com.whu.books.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
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
    @OneToOne
    private Reader reader;
    private LocalDateTime lendDate;
    private LocalDateTime backDate;
}
