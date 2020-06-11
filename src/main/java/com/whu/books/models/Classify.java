package com.whu.books.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @class Classify
 * @description 
 * @author 奥力给
 * @date 2020.06.11 19:03
 */

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Classify {
    String name;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
