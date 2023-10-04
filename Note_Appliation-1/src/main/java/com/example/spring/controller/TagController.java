package com.example.spring.controller;


import com.example.spring.entity.Tag;
import com.example.spring.services.TagService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/tags")
public class TagController {

	@Autowired
    private final TagService tagService;

    
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping("/add")
    public ResponseEntity<Tag> addTag(@RequestBody Tag tag) {
        Tag newTag = tagService.addTag(tag);
        return new ResponseEntity<>(newTag, HttpStatus.CREATED);
    }

    @PostMapping("/add-to-note/{noteId}")
    public ResponseEntity<Set<Tag>> addTagsToNote(
            @PathVariable int noteId,
            @RequestBody Set<Tag> tagsToAdd
    ) {
        Set<Tag> addedTags = tagService.addTagsToNote(noteId, tagsToAdd);
        return new ResponseEntity<>(addedTags, HttpStatus.OK);
    }

    @DeleteMapping("/remove/{tagId}")
    public ResponseEntity<String> removeTag(@PathVariable int tagId) {
        tagService.removeTag(tagId);
        return new ResponseEntity<>("Tag with ID " + tagId + " has been removed.", HttpStatus.NO_CONTENT);
    }
}
