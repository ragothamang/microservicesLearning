package com.example.demo;

import java.net.URI;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RestController
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	MessageSource messageSource;
	
	 @GetMapping(path = "/users")
	    public List<User> getAllUsers(){
	       return userService.retrieveAllUsers();
	    }
	
	@GetMapping(path = "/users/{userId}")
	public User getSpecificUser(@PathVariable int userId) {
		User user = UserService.getUser(userId);		
		return user;
	}
	
	@GetMapping(path = "/usersWithHeaderDetails/{userId}")
	public ResponseEntity<User> getSpecificUserWithHeaderDetails(@PathVariable int userId) {
		User user = UserService.getUser(userId);
//		URI uri = ServletUriComponentsBuilder.
//				fromCurrentRequest().
//				path("/{id}").
//				buildAndExpand(user.getId()).toUri();
		
//		return ResponseEntity.ok(user);
		return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);
	}
	
	
	@PostMapping(path = "/users")
	public User createUser(@RequestBody User user) {
		User newUser = userService.addUser(user);
		return newUser;
	}
	
	@PostMapping(path = "/usersWithHeaders", consumes = "application/json",
			produces = "application/json")
	public ResponseEntity<User> createUserWithHeader(@RequestBody User user) {
		User newUser = userService.addUser(user);
		  if (newUser==null) {
	            return ResponseEntity.internalServerError().body(newUser);
	        }
		URI uri = ServletUriComponentsBuilder.fromCurrentServletMapping().
//				fromCurrentRequest().
				path("/users/{id}").
				buildAndExpand(newUser.getId()).toUri();
		return ResponseEntity.created(uri).body(newUser);
	}
	
	@DeleteMapping(path = "/usersWithHeaderDetails/{userId}")
	public ResponseEntity deleteUser(@PathVariable int userId) {
		userService.deleteUser(userId);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(path = "/user/{Id}")
	public ResponseEntity dUser(@PathVariable int Id, @RequestBody User user) {
		if(user.getName()==null) return ResponseEntity.badRequest().build();
//		if(user.getName()==null) return ResponseEntity.internalServerError().build();
		User updatedUser = userService.updateUser(Id, user);
		return new ResponseEntity(userService.updateUser(Id, user),HttpStatus.OK);
//		return ResponseEntity.ok().body(updatedUser);
	}
	
	/*
	 *  Assignment - 26-Feb-2023 
	 * Assignment - Patch implementation
	 */
	
	@PatchMapping(path = "/userPatch/{id}")
	public ResponseEntity<User> patchUser(@PathVariable int id, @RequestBody Map<String, Object> updates) {
		User updatedUser = userService.patchUser(0, updates);
		return new ResponseEntity(updatedUser,HttpStatus.OK);
	}
	
	
	/*
	 * Assignment - 26-Feb-2023
	 * 1. implementation of Query params
	 * 2. i18n handling for error message	 * 
	 */
	@GetMapping(path="/usersByQueryParam", consumes = "application/json",
			produces = "application/json")
    public ResponseEntity getAllUsersByQueryParam(@RequestParam("name") String name){
		User user = UserService.getUserByName(name);
		Locale locale = LocaleContextHolder.getLocale();
		if(user==null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageSource.getMessage("error.message.user.not.found",null,locale));
				
		
//		System.out.println("rg get user name   -->"+ user.getName());
	       return new ResponseEntity<User>(user, HttpStatus.OK);
	       
	    }	
	
	/*
	 * 
	 * Assignment - POJO
	 */
}
