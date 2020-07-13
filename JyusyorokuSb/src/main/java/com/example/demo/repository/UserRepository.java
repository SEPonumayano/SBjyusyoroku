package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	//一覧
	@Query(value = "SELECT * FROM jyusyoroku WHERE delete_flg = 0", nativeQuery=true)
	 List<User> searchAll();

	//総件数
	@Query(value = "SELECT count(*) FROM jyusyoroku WHERE delete_flg = 0", nativeQuery=true)
	int findAllCnt();

	@Query(value = "SELECT * FROM jyusyoroku WHERE delete_flg = 0 LIMIT :startIndex OFFSET :pageSize ", nativeQuery=true)
	List<User> findListPaging(@Param("startIndex") int startIndex, @Param("pageSize") int pageSize);


	//検索
	@Query(value = "SELECT * FROM jyusyoroku WHERE delete_flg = 0 AND address LIKE %:keyword% ", nativeQuery=true)
	 List<User> searchPoint(@Param("keyword") String keyword) ;




}

