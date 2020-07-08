package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	@Query(value = "SELECT * FROM jyusyoroku WHERE delete_flg = 0", nativeQuery=true)
	 List<User> searchAll();

	//@SetParametr("Keywords");
	@Query(value = "SELECT * FROM jyusyoroku WHERE delete_flg = 0 AND address LIKE %:keywords%  ", nativeQuery=true)
	 List<User> searchPoint();





}

