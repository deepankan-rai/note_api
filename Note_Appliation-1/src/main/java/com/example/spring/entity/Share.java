package com.example.spring.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="shares")
public class Share {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="link_id")
	private int linkId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="note_id",nullable=false)
	private Notes note;
	
	 @Column(name = "token", unique = true, nullable = false)
	 private String token;

    @Column(name = "expiry_date", nullable = false)
    private LocalDateTime expiryDate;


    @Column(name = "is_active", nullable = false)
    private boolean isActive;
    
    public Share() {
		// TODO Auto-generated constructor stub
	}

	public int getLinkId() {
		return linkId;
	}

	public void setLinkId(int linkId) {
		this.linkId = linkId;
	}

	public Notes getNote() {
		return note;
	}

	public void setNote(Notes note) {
		this.note = note;
	}

	public LocalDateTime getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDateTime expiryDate) {
		this.expiryDate = expiryDate;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Share(int linkId, Notes note, String token, LocalDateTime expiryDate, boolean isActive) {
		super();
		this.linkId = linkId;
		this.note = note;
		this.token = token;
		this.expiryDate = expiryDate;
		this.isActive = isActive;
	}

	
    
    
}
