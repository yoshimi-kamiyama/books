package com.eightbit.books.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eightbit.books.entity.Books;

@Repository
public interface BooksRepository extends JpaRepository<Books, Long> {
	
	/**
	 * Booksエンティティの中でTitleに指定の文字列が含まれるレコードを取得する
	 * SELECT * FROM books WHERE title LIKE '%bookName%';
	 */
	List<Books> findByTitleContaining(String bookName);
	
	/**
	 * Booksエンティティの中でAuthorに指定の文字列が含まれるレコードを取得する
	 * SELECT * FROM books WHERE author LIKE '%authName%';
	 */
	List<Books> findByAuthorContaining(String authName);
	
	/**
	 * BooksエンティティのすべてのレコードをTitleの昇順で取得する
	 * SELECT * FROM books ORDER BY title ASC;
	 */
	List<Books> findAllByOrderByTitleAsc();
	
	/**
	 * Booksエンティティの特定のgenreIdに一致するすべてのレコードを取得する
	 * SELECT * FROM books WHERE genre_genre_id = :genreId;
	 */
	List<Books> findByGenreGenreId(int genreId);
	
	/**
	 * Booksエンティティの特定のbookIdに一致するすべてのレコードを取得する
	 * SELECT * FROM books WHERE id = bookId;
	 */
	Books findByBookId(int bookId);
}
