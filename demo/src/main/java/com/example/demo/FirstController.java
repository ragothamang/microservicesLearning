package com.example.demo;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class FirstController {
	@RequestMapping(path="/api/greeting", method = RequestMethod.GET)
	public String greeting() {
		return "Welcome R8G";
	}

	@GetMapping(path = "/employees")
	public List<Employee> getEmployees(){
	Employee roger = Employee.builder().id(1).name("Roger").dob(LocalDate.now().minusYears(20)).build();
    Employee rafa = Employee.builder().id(2).name("Rafa").dob(LocalDate.now().minusYears(15)).build();
    return Arrays.asList(roger,rafa);
	}
	
	@Autowired
	MessageSource messageSource;
	
	@GetMapping(path = "/greetings")
	public String getGreetings(){
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage("welcome.message", null,"default message rg1",locale);
	}
	
}
