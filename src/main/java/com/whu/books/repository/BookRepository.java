package com.whu.books.repository;

import com.whu.books.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @interface BookRepository
 * @description 
 * @author 奥力给
 * @date 2020.06.11 22:17
 */
public interface BookRepository extends JpaRepository<Book, Long> {
}
