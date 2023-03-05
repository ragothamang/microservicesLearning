package com.example.demo;

import java.net.URI;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
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
import com.example.demo.repository.UserJPARepository;
import com.example.demo.service.UserJPAService;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;


@RestController
public class UserJPAController {
//	
	@Autowired
	MessageSource messageSource;
	@Autowired
	UserJPAService userJPAService;
	
	 @GetMapping(path = "/users/jpa")
	    public List<User> getAllUsers(){
	       return userJPAService.retrieveAllUsers();
	    }
	
	@GetMapping(path = "/users/jpa/{userId}")
	public User getSpecificUserJPA(@PathVariable int userId) {
		User user = userJPAService.getUser(userId);		
		return user;
	}	
	
	@PostMapping(path = "/user/jpa/")
	public User getSpecificUser(@RequestBody User user) {
		User newUser = userJPAService.addUser(user);		
		return newUser;
	}
	
}

