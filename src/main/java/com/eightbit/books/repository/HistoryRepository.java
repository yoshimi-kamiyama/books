package com.eightbit.books.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eightbit.books.entity.History;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
	
	/**
	 * Historyエンティティに対して、CheckoutDateカラムを降順にソートしてすべてのレコードを取得
	 * SELECT * FROM history ORDER BY checkout_date DESC;
	 */
	List<History> findAllByOrderByCheckoutDateDesc();
	
	/**
	 * Historyエンティティに対して指定された日付範囲内のCheckoutDateカラムを降順にソートしてレコードを取得
	 * SELECT * FROM history WHERE checkout_date BETWEEN :since AND :until ORDER BY checkout_date DESC;
	 */
	List<History> findByCheckoutDateBetweenOrderByCheckoutDateDesc(Date since, Date until);
	
	/**
	 * Historyエンティティに対して指定された条件で検索を行う
	 * SELECT * FROM history 
	 * WHERE checkout_date BETWEEN :since AND :until 
	 * AND books_book_id IN (:bookId) 
	 * ORDER BY checkout_date DESC;
	 */
	List<History> findByCheckoutDateBetweenAndBooksBookIdInOrderByCheckoutDateDesc(Date since, Date until,
			List<Integer> bookId);
	
	/**
	 * Historyエンティティに対して指定された条件で検索を行う
	 * SELECT * FROM history 
	 * WHERE checkout_date BETWEEN :since AND :until 
	 * AND user_user_id IN (:userId) 
	 * ORDER BY checkout_date DESC;
	 */
	List<History> findByCheckoutDateBetweenAndUserUserIdInOrderByCheckoutDateDesc(Date since, Date until,
			List<Integer> userId);
	
	/**
	 * Historyエンティティに対して指定された条件で検索を行う
	 * SELECT * FROM history 
	 * WHERE checkout_date BETWEEN :since AND :until 
	 * AND books_book_id IN (:bookId) 
	 * AND user_user_id IN (:userId)
	 * ORDER BY checkout_date DESC;
	 */
	List<History> findByCheckoutDateBetweenAndBooksBookIdInAndUserUserIdInOrderByCheckoutDateDesc(Date since,
			Date until, List<Integer> bookId, List<Integer> userId);
	
	/**
	 * Historyエンティティに対して指定された条件で検索を行う
	 * SELECT * FROM history WHERE books_book_id = :bookID;
	 */
	List<History> findByBooksBookId(int bookId);
	
	/**
	 * Historyエンティティに対して指定された条件で検索を行う
	 * SELECT * FROM history WHERE user_user_id = :userID;
	 */
	List<History> findByUserUserId(int userId);
	
	/**
	 * Historyエンティティに対して指定された条件で検索を行う
	 * SELECT * FROM history WHERE id = :historyID;
	 */
	History findById(int historyId);
}