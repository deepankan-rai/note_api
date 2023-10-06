package com.example.spring.services;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.entity.Notes;
import com.example.spring.entity.Share;
import com.example.spring.repository.ShareRepository;

@Service
public class ShareService {

	@Autowired
	private ShareRepository shareRepo;
	
	
	
	 public Share generatePublicLink(Notes note, LocalDateTime expirationDate) {
	        String token = UUID.randomUUID().toString();
	       
	        Share publicLink = new Share();
	        publicLink.setNote(note);
	        publicLink.setToken(token);
	        publicLink.setExpiryDate(expirationDate);
	        return shareRepo.save(publicLink);
	    }
	 
	    public Optional<Notes> getNoteByPublicLink(String token) {
	        Optional<Share> publicLink = shareRepo.findByToken(token);

	        if (publicLink.isPresent() && publicLink.get().isActive()) {
	            return Optional.of(publicLink.get().getNote());
	        } else {
	            return Optional.empty();
	        }
	    }
	    
	    public void expirePublicLink(String token) {
	        Optional<Share> publicLink = shareRepo.findByToken(token);

	        if (publicLink.isPresent()) {
	            publicLink.get().setActive(false);;
	            shareRepo.save(publicLink.get());
	        }
	    }


}
