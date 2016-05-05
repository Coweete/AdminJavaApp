package com.essence;

import com.essence.Model.Account;
import com.essence.Service.SpringService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.Base64Utils;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertThat;


public class AdminJavaAppApplicationTests {

	//private static final Logger log = LoggerFactory.getLogger(AdminJavaAppApplicationTests.class);
	private static  String ip = "";

	private static final Logger log = LoggerFactory.getLogger(AdminJavaAppApplicationTests.class);

	@Test
	public void testGetAllUsers() {

		log.info("get all accounts");
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

		HttpHeaders headers = new HttpHeaders();
		String plainCreds = "admin:pass";
		log.info(plainCreds);
		//// TODO: 2016-05-04 sätta pass och user till null
		byte[] plainCredsByte = plainCreds.getBytes();
		byte[] base64 = Base64Utils.encode(plainCredsByte);
		String encryptedString = new String(base64);
		headers.add("Authorization", "Basic " + encryptedString);

		HttpEntity<?> requestEntity = new HttpEntity<Object>(headers);
		//ustsidan 195.178.224.74
		//insidan 172.16.2.12
		ResponseEntity<Account[]> responseEntity = restTemplate.exchange("http://172.16.2.12:44344/api/users",
				HttpMethod.GET,requestEntity,Account[].class);

		log.info(responseEntity.getStatusCode().toString() + responseEntity.hasBody());
		Account[] lista = responseEntity.getBody();
//		log.info(responseEntity.getBody().toString());
		//for (Account account : lista) {
		//	System.out.println("acc "+account.toString());
		//}
	}

	@Test
	public void testAddAccount() throws Exception {

		Account user = new Account();
		user.setFirstName("hello");
		user.setLastName("lolo");
		user.setUsername("jasdj");
		user.setPassword("pass");
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

		HttpHeaders headers = new HttpHeaders();
		String plainCreds = "admin:pass";
		//username = password = null;
		log.info("Userinfo: " + plainCreds);
		byte[] plainCredsByte = plainCreds.getBytes();
		byte[] base64 = Base64Utils.encode(plainCredsByte);
		String encryptedString = new String(base64);
		headers.add("Authorization", "Basic " + encryptedString);

		//HttpEntity<Account> request = new HttpEntity<>(user,headers);


		HttpEntity<Account> requestEntity = new HttpEntity<>(user,headers);
		//utsidan 195.178.224.74
		//insidan 172.16.2.12
		ResponseEntity<Account> response = restTemplate.exchange("http://172.16.2.12:44344/api/users",
				HttpMethod.POST,requestEntity,Account.class);
		log.info("resp "+response.getBody());
		log.info("resp "+response.getStatusCode());
	}

	@Test
	public void testGetUser() throws Exception{
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

		HttpHeaders headers = new HttpHeaders();
		String plainCreds = "admin:pass";
		//username = password = null;
		log.info("Userinfo: " + plainCreds);
		byte[] plainCredsByte = plainCreds.getBytes();
		byte[] base64 = Base64Utils.encode(plainCredsByte);
		String encryptedString = new String(base64);
		headers.add("Authorization", "Basic " + encryptedString);

		HttpEntity<Account> requestEntity = new HttpEntity<>(headers);

		ResponseEntity<Account> responseEntity = restTemplate.exchange("http://172.16.2.12:44344/api/users/" +
						"5729ee1fa889b55438cfbea0",
				HttpMethod.GET,requestEntity,Account.class);

		log.info("User " + responseEntity.hasBody());
		log.info("User " + responseEntity.getBody().toString());

	}

	@Test
	public void testUpdateUser(){

		SpringService service = new SpringService();
		Account user = service.getUser();
		user.setFirstName("cancer");
		user.setLastName("aids");

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

		HttpHeaders headers = new HttpHeaders();
		String plainCreds = "admin:pass";
		//username = password = null;
		log.info("Userinfo: " + plainCreds);
		byte[] plainCredsByte = plainCreds.getBytes();
		byte[] base64 = Base64Utils.encode(plainCredsByte);
		String encryptedString = new String(base64);
		headers.add("Authorization", "Basic " + encryptedString);

		//HttpEntity<Account> request = new HttpEntity<>(user,headers);

		HttpEntity<Account> requestEntity = new HttpEntity<>(user,headers);
		//utsidan 195.178.224.74
		//insidan 172.16.2.12
		ResponseEntity<Account> response = restTemplate.exchange("http://172.16.2.12:44344/api/users/"
				+ "5729ee1fa889b55438cfbea0",
				HttpMethod.PUT,requestEntity,Account.class);
		log.info("resp "+response.getBody());
		log.info("resp "+response.getStatusCode());
	}

	@Test
	public void testDelete(){
		SpringService service = new SpringService();
		Account newUser = service.addUser(new Account());

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

		HttpHeaders headers = new HttpHeaders();
		String plainCreds = "admin:pass";
		//username = password = null;
		log.info("Userinfo: " + plainCreds);
		byte[] plainCredsByte = plainCreds.getBytes();
		byte[] base64 = Base64Utils.encode(plainCredsByte);
		String encryptedString = new String(base64);
		headers.add("Authorization", "Basic " + encryptedString);

		HttpEntity<Account> requestEntity = new HttpEntity<>(newUser,headers);
		//utsidan 195.178.224.74
		//insidan 172.16.2.12
		ResponseEntity<Account> response = restTemplate.exchange("http://172.16.2.12:44344/api/users/" +
				newUser.getId()
				,HttpMethod.DELETE,requestEntity,Account.class);
		log.info("resp "+response.getBody());
		log.info("resp "+response.getStatusCode());
	}

}
