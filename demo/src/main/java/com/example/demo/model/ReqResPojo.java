package com.example.demo.model;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import com.example.demo.model.UserV2.Address;
import com.example.demo.model.UserV2.Name;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 *  Assignment - 26-Feb-2023
 * POJO assignment
 */

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ReqResPojo {
	private int page;
	private int per_page;
	private int total;
	private int total_pages;
	private List<ReqResUser> data;
	private Support support;
	
	@Data
	@Builder(toBuilder = true)
	@AllArgsConstructor
	@NoArgsConstructor
	public static class ReqResUser{
		private int id;
		private String email;
		private String firstName;
		private String lastName;
		private URI avatar;
	}

	@Data
	@Builder(toBuilder = true)
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Support{
		
		private String text;
		private URI url;
	}
}
