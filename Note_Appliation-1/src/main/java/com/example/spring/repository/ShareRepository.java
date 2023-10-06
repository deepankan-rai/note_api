package com.example.spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring.entity.Share;

public interface ShareRepository extends JpaRepository<Share, Integer>{

	Optional<Share> findByToken(String token);

}
