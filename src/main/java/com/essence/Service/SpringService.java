package com.essence.Service;

import com.essence.Model.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by jonatan on 2016-04-20.
 */
@Component
public class SpringService {

    private String ip = "";
    private static final Logger log = LoggerFactory.getLogger(SpringService.class);
    private BasicAuthRestTemplate restTemplate;


    public void setIp(String ip){
        this.ip = ip;
    }

    public Account[] getAllUsers(){
        log.info("All users");
        //169.254.25.235:44344
        //Account[] allUsers = restTemplate.getForObject("https://projektessence.se/api/users",Account[].class);
        Account[] allUsers = restTemplate.getForObject("http://169.254.25.235:44344/api/users",Account[].class);
        log.info(Arrays.toString(allUsers));
        return allUsers;
    }

    public void updateUser(Account account){
        log.info(account.toString());
        restTemplate.put("http://169.254.25.235:44344/api/users/" + account.getId(),Account.class,account);
    }

    public Account login(String username, String password){
        restTemplate = new BasicAuthRestTemplate(username, password);
        String userCredentials = encryptAuthentication(username,password);
        BasicAuthRestTemplate.trustSelfSignedSSL();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        //169.254.25.235:44344
        //Map<String,Object> response = restTemplate.getForObject("https://projektessence.se/api/account",Map.class);
        Map<String,Object> response = restTemplate.getForObject("http://169.254.25.235:44344/api/account",Map.class);
        log.info(response.toString());

        Account adminAcc = new Account();
        LinkedHashMap<String,Object> responseMap = (LinkedHashMap<String,Object>) response;
        ArrayList<String> auth = (ArrayList<String>) response.get("authorities");
        log.info(auth.toString());
        LinkedHashMap<String,Object> accountMap = (LinkedHashMap<String,Object>) responseMap.get("principal");
        adminAcc.createAccountFromMap(accountMap,userCredentials,auth);
        log.info("Account: " + adminAcc.toString());
        return null;
    }

    public void addUser(Account user){
        //169.254.25.235:44344
        //Account res = restTemplate.postForObject("https://projektessence.se/api/users",user,Account.class);
        Account res = restTemplate.postForObject("http://169.254.25.235:44344/api/users",user,Account.class);
    }

    public Account getUser(String id){
        Account recived = restTemplate.getForObject("https://projektessence.se/api/users" + id,Account.class);
        log.info(recived.toString());
        return recived;
    }


    public void deleteUser(Account user){
      restTemplate.delete("http://169.254.25.235:44344/api/users/" + user.getId(), Account.class);
    }

    private String encryptAuthentication(String username, String password) {
        String plainCreds = username + ":" + password;
        byte[] plainCredsByte = plainCreds.getBytes();
        byte[] base64 = Base64Utils.encode(plainCredsByte);
        return new String(base64);
    }
}

