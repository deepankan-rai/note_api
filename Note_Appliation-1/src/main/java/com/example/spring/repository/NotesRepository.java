package com.example.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring.entity.Notes;

public interface NotesRepository extends JpaRepository<Notes, Integer>{
	 List<Notes> findByUserDtlsId(int userId);

}