package com.essence.Service;

import com.essence.Model.Account;
import com.essence.Model.RfidKey;
import com.essence.Model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jonatan on 2016-04-20.
 */
@Component
public class SpringService {

    private String ip = "";
    private static final Logger log = LoggerFactory.getLogger(SpringService.class);
    private RestTemplate restTemplate = new RestTemplate();
    private User getUser;

    public void setIp(String ip){
        this.ip = ip;
    }

    public  void getAllUsers(String username, String passWord){
        log.info("get all accounts");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        HttpHeaders headers = new HttpHeaders();
        String plainCreds = username + ":" + passWord;
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

        //LinkedHashMap<String,Object> response = (LinkedHashMap<String, Object>) responseEntity.getBody();
       // Account[] users = (Account[]) response;


        log.info(responseEntity.getStatusCode().toString() + responseEntity.hasBody());
        log.info(responseEntity.getBody().toString());


        //return null;
    }

    public void updateUser(User user){
        //TODO FIX THIS SHIT
    }

    public void login(String username, String password){
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

        getAllUsers(username,password);

        /*
        LinkedHashMap<String, Object> responseMap = (LinkedHashMap<String, Object>) response.getBody();
        LinkedHashMap<String, Object> accountMap = (LinkedHashMap<String, Object>) responseMap.get("principal");
        LinkedHashMap<String, Object> rfidMap = (LinkedHashMap<String, Object>) accountMap.get("rfidKey");

        Account acc = new Account();

        acc.setFirstName((String) accountMap.get("firstName"));
        acc.setLastName((String) accountMap.get("lastName"));
        acc.setId((String) accountMap.get("id"));
        acc.setAccountNonExpired((Boolean) accountMap.get("accountNonExpired"));
        //acc.setIsEnabled((Boolean) accountMap.get("enabled"));
        acc.setRfidKey(new RfidKey((String) rfidMap.get("id")));

        */
    }
}

