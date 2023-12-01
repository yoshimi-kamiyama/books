package com.eightbit.books.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eightbit.books.entity.LoginUser;

@Repository
public interface LoginUserRepository extends JpaRepository<LoginUser, Long> {

	LoginUser findByEmail(String email);
}
