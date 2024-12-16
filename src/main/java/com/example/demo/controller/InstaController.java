package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.InstaUser;
import com.example.demo.service.InstaService;

@RestController
@RequestMapping("/api/instagram")
public class InstaController {
	
	@Autowired
	private InstaService instaService;
	
	
	// save user
	@PostMapping("/user")
	public ResponseEntity<?> registerUser(@RequestBody InstaUser user) {
		try {
			InstaUser savedUser = instaService.saveUser(user);
			return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

		}
	}
	
	
	// get user by id
	@GetMapping("/{id}")
	public  Optional<InstaUser> GetuserById(@PathVariable int id){
		return instaService.getUserById(id);
	}
	
	//get user By username 
	@GetMapping("/username/{username}")
	public InstaUser GetuserByusername(@PathVariable String username) {
		return instaService.getUserByUsername(username);
	}
	
	// Get All user 
	@GetMapping("/alluser")
	public List<InstaUser> getAlluser(){
		return instaService.getAllUsers();
	}
	
	// Delete user By id
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteById(@PathVariable int id) {
		boolean isDeleted = instaService.deleteById(id);
		if (isDeleted) {
			return ResponseEntity.ok("User deleted successfully");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
		}
		
   
	}
	

	
	// put User date update
	@PutMapping("/{id}")
	public ResponseEntity<InstaUser> updateUser(@PathVariable Long id, @RequestBody InstaUser userDetails){
		try {
			InstaUser updateUser = instaService.updateUser(id, userDetails);
			return new ResponseEntity<>(updateUser,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}


}
