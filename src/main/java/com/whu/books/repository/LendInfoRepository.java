package com.whu.books.repository;

import com.whu.books.models.LendInfo;
import com.whu.books.models.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @class LendInfoRepository
 * @description 
 * @author 奥力给
 * @date 2020.06.11 22:18
 */
@Repository
public interface LendInfoRepository extends JpaRepository<LendInfo, Long> {
    List<LendInfo> findByReader(Reader reader);
}
