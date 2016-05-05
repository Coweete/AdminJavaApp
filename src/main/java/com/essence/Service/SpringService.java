package com.essence.Service;

import com.essence.Model.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by jonatan on 2016-04-20.
 */
@Component
public class SpringService {

    private String ip = "";
    private static final Logger log = LoggerFactory.getLogger(SpringService.class);
    private RestTemplate restTemplate = new RestTemplate();


    public void setIp(String ip){
        this.ip = ip;
    }

    public Account[] getAllUsers(String encryptedString){
        log.info("get all accounts");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + encryptedString);

        HttpEntity<?> requestEntity = new HttpEntity<Object>(headers);
        //ustsidan 195.178.224.74
        //insidan 172.16.2.12
        ResponseEntity<Account[]> response = restTemplate.exchange("http://172.16.2.12:44344/api/users",
                HttpMethod.GET,requestEntity,Account[].class);

        log.info(response.getStatusCode().toString() + response.hasBody());
        Account[] res = response.getBody();
        return res;
    }

    public void updateUser(Account account){
        log.info("Spring updateUser");
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


        HttpEntity<Account> requestEntity = new HttpEntity<>(account,headers);
        //utsidan 195.178.224.74
        //insidan 172.16.2.12
        ResponseEntity<Account> response = restTemplate.exchange("http://172.16.2.12:44344/api/users/"
                        + account.getId(),
                HttpMethod.PUT,requestEntity,Account.class);
        log.info("resp "+response.getBody());
        log.info("resp "+response.getStatusCode());
    }

    public Account login(String username, String password){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        HttpHeaders headers = new HttpHeaders();
        String plainCreds = username + ":" + password;
        //username = password = null;
        byte[] plainCredsByte = plainCreds.getBytes();
        byte[] base64 = Base64Utils.encode(plainCredsByte);
        String encryptedString = new String(base64);
        headers.add("Authorization", "Basic " + encryptedString);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        //utsidan 195.178.224.74
        //insidan 172.16.2.12
        ResponseEntity<Map> response = restTemplate.exchange("http://172.16.2.12:44344/api/account",
                HttpMethod.GET, requestEntity, Map.class);

        log.info(response.getStatusCode().toString() + response.hasBody());
        log.info(response.getBody().toString());

        LinkedHashMap<String, Object> accountMap = (LinkedHashMap<String, Object>) response.getBody().get("principal");
        Account acc = new Account();
        acc.createAccountFromMap(accountMap,encryptedString);
        return acc;
    }

    public Account addUser(Account user){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        HttpHeaders headers = new HttpHeaders();
        String plainCreds = "admin:pass";
        log.info("Userinfo: " + plainCreds);
        byte[] plainCredsByte = plainCreds.getBytes();
        byte[] base64 = Base64Utils.encode(plainCredsByte);
        String encryptedString = new String(base64);
        headers.add("Authorization", "Basic " + encryptedString);


        HttpEntity<Account> requestEntity = new HttpEntity<>(user,headers);
        //utsidan 195.178.224.74
        //insidan 172.16.2.12
        ResponseEntity<Account> response = restTemplate.exchange("http://172.16.2.12:44344/api/users",
                HttpMethod.POST,requestEntity,Account.class);

        log.info("resp "+response.getBody());
        log.info("resp "+response.getStatusCode());

        return response.getBody();
    }

    public Account getUser(){

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

        Account recived = responseEntity.getBody();
        return recived;
    }


    public Account deleteUser(Account user){
        log.info("spring delete user " + user.getId());
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

        HttpEntity<Account> requestEntity = new HttpEntity<>(user,headers);
        //utsidan 195.178.224.74
        //insidan 172.16.2.12
        ResponseEntity<Account> response = restTemplate.exchange("http://172.16.2.12:44344/api/users/" + user.getId()
                ,HttpMethod.DELETE,requestEntity,Account.class);
        log.info("resp "+response.getBody());
        log.info("resp "+response.getStatusCode());
        return response.getBody();
    }
}

