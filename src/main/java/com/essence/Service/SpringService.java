package com.essence.Service;

import com.essence.Model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

/**
 * Created by jonatan on 2016-04-20.
 */
@Component
public class SpringService {

    private String ip = "";
    private static final Logger log = LoggerFactory.getLogger(SpringService.class);
    private RestTemplate restTemplate = new RestTemplate();
    private User getUser;

    public User getUser(){
        SimpleClientHttpRequestFactory requestFactory = (SimpleClientHttpRequestFactory) restTemplate.getRequestFactory();
        requestFactory.setReadTimeout(3000);
        requestFactory.setConnectTimeout(3000);
        try{
            System.out.println("spring");
            log.info("" + ip);
            //getUser = restTemplate.getForObject(ip + "/C48659EC", User.class);
            getUser = restTemplate.getForObject(ip + "/C48659EC", User.class);
            //getUser = restTemplate.getForObject("http://195.178.224.74:44344/users/C48659EC",User.class);
            log.info(getUser.toString());
            return getUser;
        }catch (Exception e){
            log.error("Error starting connection to server");
        }
        return null;
    }

    public User[] getAllUsersTest(){
        SimpleClientHttpRequestFactory requestFactory = (SimpleClientHttpRequestFactory) restTemplate.getRequestFactory();
        requestFactory.setReadTimeout(3000);
        requestFactory.setConnectTimeout(3000);
        try{
            System.out.println("SPringing");
            User[] allUsers = restTemplate.getForObject(ip, User[].class);
            //log.info("spring " + allUsers[0].getKey().getId());
            log.info(allUsers.toString());
            return allUsers;
        }catch (Exception e){
            log.error("Error med att hämta alla");
        }
        return null;
    }

    public void setIp(String ip){
        this.ip = ip;
    }
}
