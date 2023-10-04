package com.example.spring.services;


import com.example.spring.entity.Notes;
import com.example.spring.entity.Tag;
import com.example.spring.repository.TagRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class TagService {

	 @Autowired
    private TagRepository tagRepository;
	 
	 @Autowired
	 private NotesService notesService;

   
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }
    

    @Transactional
    public Tag addTag(Tag tag) {
        return tagRepository.save(tag);
    }

    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }
  
    	public Set<Tag> addTagsToNote(int noteId, Set<Tag> tags) {
    	    Optional<Notes> existingNote = notesService.getNoteById(noteId);
    	    if (existingNote.isPresent()) {
    	        Set<Tag> updatedTags = new HashSet<>();
    	        for (Tag tag : tags) {
    	            Optional<Tag> existingTag = tagRepository.findByTagName(tag.getTagName());
    	            if (existingTag.isPresent()) {
    	                updatedTags.add(existingTag.get());
    	            } else {
    	                Tag savedTag = tagRepository.save(tag); 
    	                updatedTags.add(savedTag);
    	            }
    	        }
    	        existingNote.get().setTags(updatedTags);
    	        notesService.updateNote(existingNote.get());
    	        return updatedTags;
    	    } else {
    	        throw new IllegalArgumentException("Note not found with id: " + noteId);
    	    }
    	}


    public void removeTag(int tagId) {
        Optional<Tag> existingTag = tagRepository.findById(tagId);
        if (existingTag.isPresent()) {
            tagRepository.deleteById(tagId);
        } else {
            throw new IllegalArgumentException("Tag not found with id: " + tagId);
        }
    }
}
