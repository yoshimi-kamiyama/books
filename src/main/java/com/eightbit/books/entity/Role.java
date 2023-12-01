package com.eightbit.books.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * Roleテーブルのプロパティと、それに対応するgetterとsetter
 */

@Entity
public class Role {

	@Id
	private int id;
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
