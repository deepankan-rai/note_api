package com.example.spring.entity;
import java.util.HashSet;
import java.util.Set;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="tags")
public class Tag {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String tagName;
	
	@ManyToMany(mappedBy ="tags")
	private Set<Notes> notes=new HashSet<>();
	
	public Tag() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public Set<Notes> getNotes() {
		return notes;
	}

	public void setNotes(Set<Notes> notes) {
		this.notes = notes;
	}

	public Tag(int id, String tagName, Set<Notes> notes) {
		super();
		this.id = id;
		this.tagName = tagName;
		this.notes = notes;
	}
	
	public Tag(String tagName) {
		this.tagName=tagName;
	}
	
	

}
