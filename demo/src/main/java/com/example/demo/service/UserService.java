package com.example.demo.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.model.User;

@Service
public class UserService {

	public static ArrayList<User> users=new ArrayList<>();
	public static int id=0;
	static {
		users.add(new User(++id, "Roger", LocalDate.now().minusYears(20)));
		users.add(new User(++id, "Rafa", LocalDate.now().minusYears(18)));
		users.add(new User(++id, "Novak", LocalDate.now().minusYears(15)));
	}
	public List<User> retrieveAllUsers() {
		return users;
	}
	public static User getUser(int userId){
		for (User user:users){
			if(user.getId()==userId)return user;
		}
		return null;
	}
	public static User getUserByName(String name){
		for (User user:users){
			
			if(user.getName().equals(name))return user;
		}
		return null;
	}
	
	public User addUser(User user){
		// User name = new User(id, "name", "");
		User newUser = User.builder().id(++id).
				name(user.getName()).dob(user.getDob()).build();

		users.add(newUser);
		return newUser;
	}
	
	public boolean deleteUser(int id) {
		boolean isTrue = users.removeIf(x->x.getId()==id);
		return isTrue;
	}
	
	public User updateUser(int id, User userWithChanges) {
		User actualUser = getUser(id);
		
		actualUser.setDob(userWithChanges.getDob());
		actualUser.setName(userWithChanges.getName());
		return actualUser;
	}
	
	/*
	 *  Assignment - 26-Feb-2023 
	 *  1. Patch method
	 */
	
	public User patchUser(int i, Map<String, Object> updates) {
		// TODO Auto-generated method stub
		User user = getUser(id);
		 if (user == null) {
		        return null;
		    }

		    for (String key : updates.keySet()) {
		        switch (key) {
		            case "name":
		            	user.setName((String)updates.get(key));
//		                user.setFirstName((String) updates.get(key));
		                break;
//		            case "lastName":
//		                user.setLastName((String) updates.get(key));
//		                break;
		            case "dob":
		            	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
		            	formatter = formatter.withLocale( Locale.US );  // Locale specifies human language for translating, and cultural norms for lowercase/uppercase and abbreviations and such. Example: Locale.US or Locale.CANADA_FRENCH
		            	LocalDate date = LocalDate.parse("2005-nov-12", formatter);
		                user.setDob(date);
		                break;
		            // handle other fields to be updated
		        }
		    }

		    User updatedUser = updateUser(user.getId(), user);

		
		return updatedUser;
	}
	
}
