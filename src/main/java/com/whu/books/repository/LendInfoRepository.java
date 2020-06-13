package com.whu.books.repository;

import com.whu.books.models.LendInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @class LendInfoRepository
 * @description 
 * @author 奥力给
 * @date 2020.06.11 22:18
 */
@Repository
public interface LendInfoRepository extends JpaRepository<LendInfo, Long> {
}
