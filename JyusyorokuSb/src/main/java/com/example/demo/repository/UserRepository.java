package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	//一覧
	//@Query(value = "SELECT * FROM jyusyoroku WHERE delete_flg = 0", nativeQuery=true)
	// List<User> searchAll();

	//総件数
	@Query(value = "SELECT * FROM jyusyoroku WHERE delete_flg = 0 ", nativeQuery=true)
	Page<User> findAllCnt(Pageable pageable);

	//検索
	@Query(value = "SELECT * FROM jyusyoroku WHERE delete_flg = 0 AND address LIKE %:keyword% ", nativeQuery=true)
	 Page<User> searchPoint(@Param("keyword")String keyword,Pageable pageable) ;


//, ROW_NUMBER() OVER(ORDER BY id ASC)AS num

	//(SELECT ROW_NUMBER() OVER (ORDER BY jyusyoroku.id) AS num)

}

