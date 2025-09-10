package com.example.demo.controller;
 
import org.springframework.beans.factory.annotation.Autowired;
 
import org.springframework.web.bind.annotation.*;
 
import com.example.demo.model.User;
 
import com.example.demo.repo.UserRepository;
 
import java.util.List;
 
@RestController
@RequestMapping("/api/users")
public class UserController {
 
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping
	public List<User> getAllUsers()
	{
		return userRepository.findAll();
	}
 
	@PostMapping
	public User createUser(@RequestBody User user)
	{
		return userRepository.save(user);
	}
	@PostMapping("/{id}")
	public User updateUserWithPost(@PathVariable Long id, @RequestBody User userDetails) {
	    User existingUser = userRepository.findById(id)
	        .orElseThrow(() -> new RuntimeException("User not found with id " + id));
 
	    existingUser.setFirstName(userDetails.getFirstName());
	    existingUser.setLastName(userDetails.getLastName());
	    existingUser.setEmail(userDetails.getEmail());
 
	    return userRepository.save(existingUser);
	}
}
 