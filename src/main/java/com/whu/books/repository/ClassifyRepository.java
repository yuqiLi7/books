package com.whu.books.repository;

import com.whu.books.models.Classify;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @interface ClassifyRepository
 * @description 
 * @author 奥力给
 * @date 2020.06.14 01:42
 */
@Repository
public interface ClassifyRepository extends JpaRepository<Classify, Long> {
    Classify findByName(String name);
}
