package com.example.demo.service;

import com.example.demo.model.InstaUser;
import com.example.demo.repository.InstaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;
import java.util.Optional;

@Service
public class InstaService {

    @Autowired
    private InstaRepository instaRepository;

    // Save user
    public InstaUser saveUser(InstaUser user) throws Exception {
      // check if a user with the same username or email already exists
    	if(instaRepository.findByUsername(user.getUsername()) != null) {
    		throw new Exception("Username Already exists!");
    	}
    	if(instaRepository.findByEmail(user.getEmail()) !=null) {
    		throw new Exception("Email Already exists!");
    	}
		return instaRepository.save(user); // save the user if no duplicates exist
    }

    // Get user by ID
    public Optional<InstaUser> getUserById(int id) {
        return instaRepository.findById(id);
    }

    // Get user by username
    public InstaUser getUserByUsername(String username) {
        return instaRepository.findByUsername(username);
    }

	// Get all users
	public List<InstaUser> getAllUsers() {
		return instaRepository.findAll();
	}

	@DeleteMapping("/delete")
	public boolean deleteById(int id) {
		if(instaRepository.existsById(id)) {
			instaRepository.deleteById(id);   //Delete the user By Id
			return true;     //Return true indicating successfully deletion
		}
		return false;  // Return false if user doesn't exist 
	}
	
//	private Map<Long, InstaUser> instausers = new HashMap<>();
	
	public InstaUser updateUser(Long id, InstaUser userDelaits) throws Exception {
		Optional<InstaUser> optionalUser = instaRepository.findById(id);
		if(optionalUser.isPresent()) {
			InstaUser existingUser = optionalUser.get();
			existingUser.setUsername(userDelaits.getUsername());
			existingUser.setEmail(userDelaits.getEmail());
			return instaRepository.save(existingUser);//save the updated user to the database
 		}else {
 			throw new Exception("User with Id " + id + "not fount");
 		}
	}
    
	
}
