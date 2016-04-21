package com.essence.Service;

import com.essence.Model.User;
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

    public User getUser(){
        SimpleClientHttpRequestFactory requestFactory = (SimpleClientHttpRequestFactory) restTemplate.getRequestFactory();
        requestFactory.setReadTimeout(3000);
        requestFactory.setConnectTimeout(3000);
        try{
            User getUser = restTemplate.getForObject(ip + "/C48659EC", User.class);
            log.info(getUser.toString());
            return getUser;
        }catch (Exception e){
            log.error("Error starting connection to server");
        }
        return null;
    }

    public ArrayList<User> getAllUsers(){
        SimpleClientHttpRequestFactory requestFactory = (SimpleClientHttpRequestFactory) restTemplate.getRequestFactory();
        requestFactory.setReadTimeout(3000);
        requestFactory.setConnectTimeout(3000);
        try{
            System.out.println("spring");
            ArrayList<User> list = new ArrayList<>(restTemplate.getForObject(ip, ArrayList.class));
            log.info(list.toString());
            return list;
        }catch (Exception e){
            log.error("Error med att hämta alla");
        }
        return null;
    }

    public void setIp(String ip){
        this.ip = ip;
    }
}
