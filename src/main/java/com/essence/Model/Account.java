
package com.essence.Model;

import com.essence.deserializer.GrantedAuthorityDeserializer;
import com.essence.deserializer.RfidKeyDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Base64Utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by jonatan on 2016-04-28.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Account implements UserDetails, Serializable {

    @Id
    private String id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    @JsonDeserialize(using = RfidKeyDeserializer.class)
    private RfidKey rfidKey;

    @JsonDeserialize(using = GrantedAuthorityDeserializer.class)
    private List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    private boolean accountNonExpired = true;
    private boolean enabled = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private String encryptedUserCredentials;

    public Account() {
    }

    public Account(String firstName, String lastName, String username, String password, List<GrantedAuthority> authorities) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public Account(String firstName, String lastName, RfidKey rfidKey, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.rfidKey = rfidKey;
        this.authorities = AuthorityUtils.createAuthorityList(AuthoritiesConstants.USER);
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

    public RfidKey getRfidKey() {
        return rfidKey;
    }

    public void setRfidKey(RfidKey rfidKey) {
        this.rfidKey = rfidKey;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        accountNonLocked = accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public String getEncryptedUserCredentials() {
        return encryptedUserCredentials;
    }

    public void setEncryptedUserCredentials(String encryptedUserCredentials) {
        this.encryptedUserCredentials = encryptedUserCredentials;
    }

    public void createAccountFromMap(LinkedHashMap<String, Object> accountMap,String userCredentials,ArrayList<String> authorities) {
        firstName = (String) accountMap.get("firstName");
        lastName = (String) accountMap.get("lastName");
        id = (String) accountMap.get("id");
        accountNonExpired = (Boolean) accountMap.get("accountNonExpired");
        enabled = (Boolean) accountMap.get("enabled");
        if(accountMap.get("rfidKey") != null)
            rfidKey = new RfidKey((String) ((LinkedHashMap<String, Object>) accountMap.get("rfidKey")).get("id"));
        this.encryptedUserCredentials = userCredentials;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", rfidKey=" + rfidKey +
                ", authorities=" + authorities +
                ", accountNonExpired=" + accountNonExpired +
                ", enabled=" + enabled +
                ", accountNonLocked=" + accountNonLocked +
                ", credentialsNonExpired=" + credentialsNonExpired +
                '}';
    }

}
