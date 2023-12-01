package com.eightbit.books.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * login_userテーブルのプロパティと、それに対応するgetterとsetter
 */

@Entity
@Table(name = "login_user")
public class LoginUser {

	@Id
	@JoinColumn(name = "id")
	int id;

	@Column(name = "name")
	String name;

	@Column(name = "email")
	String email;

	@Column(name = "password")
	String password;

	@OneToOne
	@JoinColumn(name = "role")
	Role role;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
