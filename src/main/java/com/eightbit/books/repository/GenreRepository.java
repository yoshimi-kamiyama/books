package com.eightbit.books.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eightbit.books.entity.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

}
