package com.ameen.security.auth;

import com.ameen.security.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class AuthenticationResponse {

    private User user;
    private String token;


}
