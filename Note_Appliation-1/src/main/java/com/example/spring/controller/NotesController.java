package com.example.spring.controller;


import com.example.spring.entity.Notes;
import com.example.spring.services.NotesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NotesController {

	@Autowired
    private NotesService notesService;

    
    public NotesController(NotesService notesService) {
        this.notesService = notesService;
    }

    @PostMapping("/add")
    public ResponseEntity<Notes> addNote(@RequestBody Notes note) {
    	
        Notes newNote = notesService.addNote(note);
        return new ResponseEntity<>(newNote, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Notes>> getNotesByUserId(@PathVariable int userId) {
        List<Notes> notes = notesService.getNotesByUserId(userId);
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }

    @PutMapping("/update/{noteId}")
    public ResponseEntity<Notes> updateNote(@PathVariable int noteId, @RequestBody Notes updatedNote) {
        updatedNote.setId(noteId); // Ensure the note ID is set
        Notes updated = notesService.updateNote(updatedNote);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/remove/{noteId}")
    public ResponseEntity<String> removeNote(@PathVariable int noteId) {
        notesService.removeNote(noteId);
        return new ResponseEntity<>("Note with ID " + noteId + " has been removed.", HttpStatus.NO_CONTENT);
    }
}
