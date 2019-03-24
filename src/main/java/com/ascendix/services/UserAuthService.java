package com.ascendix.services;

import com.ascendix.models.TimeFoxUser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Base64;

public class UserAuthService {
    public HttpHeaders getBasicAuthHeaders(String user, String password) {
        String notEncoded = user + ":" + password;
        String encodedAuth = Base64.getEncoder().encodeToString(notEncoded.getBytes());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Basic " + encodedAuth);
        return headers;
    }
    public TimeFoxUser getUser() {
        TimeFoxUser user = new TimeFoxUser();
        user.setOrgId("5351");
        user.setPassword("Pe1120772_");
        user.setUserName("epanin");
        return user;
    }
}
