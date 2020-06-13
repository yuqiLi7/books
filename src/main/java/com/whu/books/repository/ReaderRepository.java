package com.whu.books.repository;

import com.whu.books.models.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @class ReaderRepository
 * @description 
 * @author 奥力给
 * @date 2020.06.11 22:18
 */
@Repository
public interface ReaderRepository extends JpaRepository<Reader, Long> {
    Reader findByPhoneAndPassword(String phone, String password);

    Reader findByPhone(String phone);

    Reader 
}
