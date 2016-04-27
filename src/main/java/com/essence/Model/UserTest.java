package com.essence.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * Created by jonatan on 2016-04-20.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserTest implements Serializable{

    private String firstName;
    private String lastName;
    private RfidKey key;

    @Id
    private String id;

    public UserTest(){

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public RfidKey getKey() {
        return key;
    }

    public void setkey(RfidKey key) {
        this.key = key;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", key=" + key +
                ", id='" + id + '\'' +
                '}';
    }
}
