package com.example.spring.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.spring.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, Integer>{
	
	 List<Tag> findByNotesId(int noteId);
	 
	 Optional<Tag> findByTagName(String tagName);
}
