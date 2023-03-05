package com.example.demo.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@NotNull(message = "id can't be empty")
	@JsonIgnore
	private int id;
	@Size(min = 2,max = 2, message = "msg > 2 and < 21")
	@JsonProperty("user_name")
	private String name;
	@Past(message = "can't be past value")
	private LocalDate dob;
}
