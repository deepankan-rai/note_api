package com.example.spring.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.entity.Collaboration;
import com.example.spring.entity.Notes;
import com.example.spring.services.CollaborationService;

@RestController
@RequestMapping("/collab")
public class CollaborationController {
	
	@Autowired
	private CollaborationService collabService;
	
	@PostMapping("/add")
    public ResponseEntity<Collaboration> addCollaborator(@RequestParam("noteId") int noteId,@RequestParam("userId") int userId,@RequestParam("role") String role) {
        Collaboration collaborator = collabService.addCollaboration(noteId, userId, role);
        return new ResponseEntity<>(collaborator, HttpStatus.CREATED);
    }
	
	@GetMapping("/note/{noteId}")
	 public ResponseEntity<List<Collaboration>> getCollaboratorsForNote(@PathVariable int noteId) {
        List<Collaboration> collaborators = collabService.getAllCollab(noteId);
        return new ResponseEntity<>(collaborators, HttpStatus.OK);
    }
	
	 @GetMapping("/user/{userId}")
	 public ResponseEntity<List<Notes>> getNotesForCollaborator(@PathVariable int userId) {
	        List<Notes> notes = collabService.getNoteForCollab(userId);
	        return new ResponseEntity<>(notes, HttpStatus.OK);
	    }

	 @DeleteMapping("/remove/{collaboratorId}")
	  public ResponseEntity<String> removeCollaborator(@PathVariable int colId) {
	        collabService.removeCollab(colId);
	        return new ResponseEntity<>("Collaborator with ID " + colId + " has been removed.", HttpStatus.NO_CONTENT);
	    }
	
}
