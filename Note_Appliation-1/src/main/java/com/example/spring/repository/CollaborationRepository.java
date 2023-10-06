package com.example.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring.entity.Collaboration;
import com.example.spring.entity.Notes;

public interface CollaborationRepository extends JpaRepository<Collaboration, Integer>{
	
	 List<Collaboration> findByNoteId(int noteId);

	List<Notes> findNotesByColId(int userId);
	 
	 

}
