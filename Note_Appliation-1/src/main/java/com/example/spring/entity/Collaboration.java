package com.example.spring.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="collaborators")
public class Collaboration {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int colId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="note_id",nullable=false)
	private Notes note;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id",nullable = false)
	private UserDtls user;
	
	private String role;
	
	public Collaboration() {
		// TODO Auto-generated constructor stub
	}

	public int getColId() {
		return colId;
	}

	public void setColId(int colId) {
		this.colId = colId;
	}

	public Notes getNote() {
		return note;
	}

	public void setNote(Notes note) {
		this.note = note;
	}

	public UserDtls getUser() {
		return user;
	}

	public void setUser(UserDtls user) {
		this.user = user;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Collaboration(Notes note, UserDtls user, String role) {
		super();
		this.note = note;
		this.user = user;
		this.role = role;
	}
	
	
}
