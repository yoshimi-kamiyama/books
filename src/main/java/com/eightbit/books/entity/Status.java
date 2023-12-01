package com.eightbit.books.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * statusテーブルのプロパティと、それに対応するgetterとsetter
 */

@Entity
@Table(name = "status")
public class Status {

	@Id
	@Column(name = "id")
	private int statusId;
	@Column(name = "status")
	private String status;

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
