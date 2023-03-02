package com.example.demo;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ReqResPojo;
import com.example.demo.model.User;
import com.example.demo.model.UserV2;

@RestController
public class VersioningController {

	@GetMapping("/users/v1")
	public User getv1Users() {
		return User.builder().id(261).name("rg 261").dob(LocalDate.now()).build();
	}
	
	@GetMapping("/users/v2")
	public UserV2 getv2User() {
		UserV2.Name name = UserV2.Name.builder().firstName("r8g").lastName(" g").build();
		UserV2.Address address = UserV2.Address.builder().city("chennai").street("main road").country("India").build();
		
		return UserV2.builder().id(26).name(name).address(address).dob(LocalDate.now().minusYears(18)).build();
	}
	
	
	/*
	 *  Assignment - 26-Feb-2023
	 * Assignment POJO construction
	 */
	@GetMapping("/users/v3")
	public ReqResPojo getv3User() {
		URI url = URI.create("https://r8g.com");
		ReqResPojo pagenation = ReqResPojo.builder().page(6).per_page(5).total(12).total_pages(2).build();
		ReqResPojo.Support support = ReqResPojo.Support.builder().url(url).text("this is support ").build();
				
		ReqResPojo.ReqResUser user1 = ReqResPojo.ReqResUser.builder().id(11).firstName("ram").build();
		ReqResPojo.ReqResUser user2 = ReqResPojo.ReqResUser.builder().id(12).firstName("siva").build();
		
		List<ReqResPojo.ReqResUser> reqrestUserList = new ArrayList<ReqResPojo.ReqResUser>();
		reqrestUserList.add(user1);
		reqrestUserList.add(user2);
		
		pagenation.setData(reqrestUserList);
		
		return pagenation.builder().page(6).per_page(5).total(12).total_pages(2).data(reqrestUserList).support(support).build();
	}
}
