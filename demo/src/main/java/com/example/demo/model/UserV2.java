package com.example.demo.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserV2 {

	private int id;
	private Name name;
	private LocalDate dob;
	private Address address;
	
	@Data
	@Builder(toBuilder = true)
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Name{
		private String firstName;
		private String lastName;
	}
	
	@Data
	@Builder(toBuilder = true)
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Address{
		private String street;
		private String city;
		private String country;
	}
}
