package com.whu.books.repository;

import com.whu.books.models.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @class ReaderRepository
 * @description 
 * @author 奥力给
 * @date 2020.06.11 22:18
 */
 
public interface ReaderRepository extends JpaRepository<Reader, Long> {
    Reader findByIDAndPassword(String phone, String password);
}
