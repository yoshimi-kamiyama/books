package com.eightbit.books.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eightbit.books.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	/**
	 * UserエンティティのLastNameカラムが指定された文字列を含む条件でエンティティを取得する
	 * SELECT * FROM user WHERE last_name LIKE '%name%';
	 */
	List<User> findByLastNameContaining(String name);
	
	/**
	 * UserエンティティのFirstNameカラムが指定された文字列を含む条件でエンティティを取得する
	 * SELECT * FROM user WHERE first_name LIKE '%name%';
	 */
	List<User> findByFirstNameContaining(String name);
	
	/**
	 * Userエンティティに対して指定されたuserIdで検索
	 * SELECT * FROM user WHERE useer_id = :userId;
	 */
	User findByUserId(int userId);
}
