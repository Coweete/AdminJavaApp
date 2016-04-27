package com.essence;

import com.essence.Model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = AdminJavaAppApplication.class)
//@WebAppConfiguration
public class AdminJavaAppApplicationTests {

	//private static final Logger log = LoggerFactory.getLogger(AdminJavaAppApplicationTests.class);
	private static  String ip = "";


	@Test
	public void testGetAllUsers() {
		/*
		RestTemplate template = new TestRestTemplate("user", "pass");
		ResponseEntity<User[]> response = template.getForEntity("http://localhost:" + port
				+ "/users", User[].class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		User[] users = response.getBody();
		System.out.println("USERS ");
		System.out.println(Arrays.toString(users));
		*/

		ip = "http://172.16.2.12:44344/users";

		RestTemplate restTemplate = new RestTemplate();
		User[] got = restTemplate.getForObject(ip, User[].class);
		//log.info(Arrays.toString(got));

		assertThat(got, notNullValue());
		//log.info("first item in list is a User");
		assertThat(got[0],instanceOf(User.class) );
		//log.info("User info: \n"+
		//		got[0].toString());
		System.out.println("got:" + got[0].toString());
	}

}
