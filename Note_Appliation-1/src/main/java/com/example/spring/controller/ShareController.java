package com.example.spring.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.entity.Notes;
import com.example.spring.entity.Share;
import com.example.spring.services.NotesService;
import com.example.spring.services.ShareService;

@RestController
@RequestMapping("/share")
public class ShareController {

	@Autowired
	private ShareService shareService;
	
	@Autowired
	private NotesService noteService;
	
	 @PostMapping("/generate/{noteId}")
	  public ResponseEntity<Share> generatePublicLink(@PathVariable int noteId,@RequestParam(name = "expirationDate") String expirationDate) {

	        LocalDateTime expiryDateTime = LocalDateTime.parse(expirationDate);

	        Optional<Notes> existingNote =noteService.getNoteById(noteId);

	        if (existingNote.isPresent()) {
	            Share publicLink = shareService.generatePublicLink(existingNote.get(), expiryDateTime);
	            return new ResponseEntity<>(publicLink, HttpStatus.CREATED);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	 
	 @GetMapping("/validate/{token}")
	 public ResponseEntity<Notes> validatePublicLink(@PathVariable String token) {
	        Optional<Notes> note = shareService.getNoteByPublicLink(token);

	        if (note.isPresent()) {
	            return new ResponseEntity<>(note.get(), HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	 
	 @PostMapping("/expire/{token}")
	 public ResponseEntity<String> expirePublicLink(@PathVariable String token) {
	        shareService.expirePublicLink(token);
	        return new ResponseEntity<>("Public link with token " + token + " has been expired.", HttpStatus.OK);
	    }
}
