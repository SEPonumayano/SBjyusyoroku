package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	@Query(value = "SELECT * FROM jyusyoroku WHERE delete_flg = 0", nativeQuery=true)
	 List<User> searchAll();


	@Query(value = "SELECT * FROM jyusyoroku WHERE delete_flg = 0 AND address LIKE %:keyword% ", nativeQuery=true)
	 List<User> searchPoint(@Param("keyword") String keyword) ;


}

