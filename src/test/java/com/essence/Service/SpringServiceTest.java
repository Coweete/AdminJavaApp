package com.essence.Service;

import org.junit.Before;

/**
 * Created by jonatan on 2016-04-25.
 */
public class SpringServiceTest {

    private SpringService service;

    @Before
    public void setUp() throws Exception {
        //service.setIp("http://195.178.224.74:44344/users");
        service.setIp("http://172.16.2.12:44344/users");
    }

}