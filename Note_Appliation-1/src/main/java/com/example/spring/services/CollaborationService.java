package com.example.spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.entity.Collaboration;
import com.example.spring.entity.Notes;
import com.example.spring.entity.UserDtls;
import com.example.spring.repository.CollaborationRepository;

@Service
public class CollaborationService {
	
	@Autowired
	private CollaborationRepository collabRepo;
	
	@Autowired
	private NotesService noteService;
	
	@Autowired
	private UserDtlsService userDltsService;
	
	public Collaboration addCollaboration(int noteId,int userId,String role) {
		Optional<Notes> existingNote=noteService.getNoteById(noteId);
		Optional<UserDtls> existingUser=userDltsService.getUserById(userId);
		
		if(existingNote.isPresent()&&existingUser.isPresent()) {
			Collaboration collab=new Collaboration(existingNote.get(),existingUser.get(),role);
			return collabRepo.save(collab);
		}else {
			throw new IllegalArgumentException("Note or User not found.");
		}
	}
	
	public List<Collaboration> getAllCollab(int noteId){
		return collabRepo.findByNoteId(noteId);
	}
	
	public List<Notes> getNoteForCollab(int userId){
		return collabRepo.findNotesByColId(userId);
	}
	
	public void removeCollab(int colId) {
		collabRepo.deleteById(colId);
	}

}
