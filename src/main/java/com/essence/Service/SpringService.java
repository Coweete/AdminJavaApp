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
        Account[] allUsers = restTemplate.getForObject("https://projektessence.se/api/users",Account[].class);
        log.info(Arrays.toString(allUsers));
        return allUsers;
    }

    public void updateUser(Account account){
        log.info(account.toString());
        HttpEntity<Account> entity = new HttpEntity<>(account);
        ResponseEntity<Account> update = restTemplate.exchange("https://projektessence.se/api/users/" + account.getId(),
                HttpMethod.PUT,entity,Account.class);
        //restTemplate.put("http://169.254.25.235:44344/api/users/" + account.getId(),Account.class,account);
        log.info("after" + update.getBody());
    }

    public Account login(String username, String password){
        //// TODO: 2016-05-12 fixa så att accountet man får hem är en admin annars ska man inte kunna använda appen
        restTemplate = new BasicAuthRestTemplate(username, password);
        String userCredentials = encryptAuthentication(username,password);
        BasicAuthRestTemplate.trustSelfSignedSSL();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        Map<String,Object> response = restTemplate.getForObject("https://projektessence.se/api/account",Map.class);
        log.info(response.toString());
        Account adminAcc = new Account();
        LinkedHashMap<String,Object> responseMap = (LinkedHashMap<String,Object>) response;
        ArrayList<String> auth = (ArrayList<String>) response.get("authorities");
        log.info(auth.toString());
        LinkedHashMap<String,Object> accountMap = (LinkedHashMap<String,Object>) responseMap.get("principal");
        adminAcc.createAccountFromMap(accountMap,userCredentials,auth);
        log.info("Account: " + adminAcc.toString());
        return adminAcc;
    }

    public void addUser(Account user){
        Account res = restTemplate.postForObject("https://projektessence.se/api/users",user,Account.class);
        log.info("Added " + res.toString());
    }

    public Account getUser(String id){
        Account recived = restTemplate.getForObject("https://projektessence.se/api/users" + id,Account.class);
        log.info(recived.toString());
        return recived;
    }


    public void deleteUser(Account account){
        log.info(account.toString());
        HttpEntity<Account> entity = new HttpEntity<>(account);
        ResponseEntity<Account> update = restTemplate.exchange("https://projektessence.se/api/users/" + account.getId(),
                HttpMethod.DELETE,entity,Account.class);
        log.info("Deleted" + update.getBody());
    }

    private String encryptAuthentication(String username, String password) {
        String plainCreds = username + ":" + password;
        byte[] plainCredsByte = plainCreds.getBytes();
        byte[] base64 = Base64Utils.encode(plainCredsByte);
        return new String(base64);
    }
}

