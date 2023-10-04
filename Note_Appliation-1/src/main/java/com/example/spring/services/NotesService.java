package com.example.spring.services;


import com.example.spring.entity.Notes;
import com.example.spring.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotesService {

	@Autowired
    private  NotesRepository notesRepository;

    
    public NotesService(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }

    public Notes addNote(Notes note) {
        return notesRepository.save(note);
    }

    public List<Notes> getNotesByUserId(int userId) {
        return notesRepository.findByUserDtlsId(userId);
    }
    
    public Optional<Notes> getNoteById(int noteId) {
        return notesRepository.findById(noteId);
    }
    

    public Notes updateNote(Notes updatedNote) {
     
        Optional<Notes> existingNote = notesRepository.findById(updatedNote.getId());
        if (existingNote.isPresent()) {
          
            Notes noteToUpdate = existingNote.get();
            noteToUpdate.setTitle(updatedNote.getTitle());
            noteToUpdate.setContent(updatedNote.getContent());

            return notesRepository.save(noteToUpdate);
        } else {
          
            throw new IllegalArgumentException("Note not found with id: " + updatedNote.getId());
        }
    }

    public void removeNote(int noteId) {
        
        Optional<Notes> existingNote = notesRepository.findById(noteId);
        if (existingNote.isPresent()) {
            notesRepository.deleteById(noteId);
        } else {
            
            throw new IllegalArgumentException("Note not found with id: " + noteId);
        }
    }
}
