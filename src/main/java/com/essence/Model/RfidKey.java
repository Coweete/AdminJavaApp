package com.essence.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by jonatan on 2016-04-20.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RfidKey implements Serializable{

    private String id;

    public RfidKey(){};

    public RfidKey(String id){
        this.id = id;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
