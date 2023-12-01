package com.eightbit.books.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * genreテーブルのプロパティと、それに対応するgetterとsetter
 */

@Entity
@Table(name = "genre")
public class Genre {

	@Id
	@Column(name = "id")
	private int genreId;
	@Column(name = "genre")
	private String genre;

	public int getGenreId() {
		return genreId;
	}

	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

}
