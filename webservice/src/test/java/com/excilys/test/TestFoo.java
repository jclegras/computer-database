package com.excilys.test;

import java.time.LocalDateTime;

import org.junit.Test;

import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class TestFoo {
	@Test
	public void test() throws JsonProcessingException {
		Company company = new Company(1L, "companyName");
		Computer computer = new Computer("computerName", LocalDateTime.now(), LocalDateTime.now(), company);
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		System.out.println(mapper.writeValueAsString(computer));
	}
}
