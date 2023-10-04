package com.example.spring.entity;

import java.util.Date;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;



@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Audit {

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_dt")
	private Date createdDate;
	
	

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_dt")
	private Date updatedDate;
	
	public Audit() {
		// TODO Auto-generated constructor stub
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Audit(Date createdDate, Date updatedDate) {
		super();
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}


	
	
}
