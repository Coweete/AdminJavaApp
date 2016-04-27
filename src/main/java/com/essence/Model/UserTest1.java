package com.essence.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * Created by Anton on 2016-04-06.
 */

/**
 * User class that contains the information about the users
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserTest1 implements Serializable {

    private String firstName;
    private String lastName;
    private RfidKey rfidKey;

    @Id
    private String id;


    /**
     * Constructor that starts the creation of a user
     **/
    public UserTest1() {

    }

    /**
     * Fetches the first name of the user
     * @return the first name
     **/
    public String getFirstName() {
        return firstName;
    }

    /**
     * Fetches the last name of the user
     * @return the last name
     **/
    public String getLastName() {
        return lastName;
    }

    /**
     *Fetches the id of the user
     * @return the id
     **/
    public String getId() {
        return id;
    }

    /**
     * Fetches the RFID of the user
     * @return the RFID
     **/
    public RfidKey getRfid() {
        return rfidKey;
    }

    /**
     * Sets a new first name of the user
     * @param firstName the new first name
     **/
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Sets a new last name for the user
     * @param lastName the new last name
     **/
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Sets a new RFID for the user
     * @param rfid the new RFID
     **/
    public void setRfid(RfidKey rfid) {
        this.rfidKey = rfid;
    }

    /**
     *Sets a new id for the user
     * @param id the new id
     **/
    public void setId(String id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", rfid='" + rfidKey + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
