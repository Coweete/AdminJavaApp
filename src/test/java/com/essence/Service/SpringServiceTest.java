package com.essence.Service;

import org.junit.Test;

/**
 * Created by jonatan on 2016-04-25.
 */
public class SpringServiceTest {


    @Test
    public void testBasic(){
        BasicAuthRestTemplate restTemplate = new BasicAuthRestTemplate("admin", "pass");
        String url = "https://projektessence.se/api/users";

        BasicAuthRestTemplate.trustSelfSignedSSL();

        String got = "";
        try {
            // Make the HTTP GET request to the Basic Auth protected URL
            got = restTemplate.getForObject(url, String.class);
            System.out.println(got);
        } catch (Exception e) {
            System.out.println("error with doing rest "+e.getMessage());
        }
    }

}