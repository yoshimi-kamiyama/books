package com.eightbit.books.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eightbit.books.entity.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {

}
