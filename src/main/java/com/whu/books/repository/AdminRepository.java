package com.whu.books.repository;

import com.whu.books.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @interface AdminRepository
 * @description 
 * @author 奥力给
 * @date 2020.06.11 19:36
 */
@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

}
