package com.agenatech.solutions.iamaie2etests.service;


import com.agenatech.solutions.iamaie2etests.client.KeycloackClient;
import com.agenatech.solutions.iamaie2etests.config.KeycloakConfig;
import com.agenatech.solutions.iamaie2etests.payload.KeycloackCredentials;
import com.agenatech.solutions.iamaie2etests.payload.request.KeycloackAdminTokenRequest;
import com.agenatech.solutions.iamaie2etests.payload.request.KeycloackSignupRequest;
import com.agenatech.solutions.iamaie2etests.payload.request.KeycloakLoginRequest;
import com.agenatech.solutions.iamaie2etests.payload.response.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class KeycloakService {
    @Autowired
    private KeycloackClient keycloackClient;
    @Autowired
    private KeycloakConfig keycloakConfig;


    public AuthResponse login(KeycloakLoginRequest loginRequest){
        return keycloackClient.login(loginRequest);
    }

    public AuthResponse defaultLogin(){
        KeycloakLoginRequest loginRequest =
                KeycloakLoginRequest.builder()
                        .client_id(keycloakConfig.getClient())
                        .grant_type(keycloakConfig.getGrantType())
                        .username(keycloakConfig.getTestUsername())
                        .password(keycloakConfig.getTestPassword())
                        .build();

        return login(loginRequest);
    }

    public AuthResponse login(String username, String password){
        KeycloakLoginRequest loginRequest =
                KeycloakLoginRequest.builder()
                        .client_id(keycloakConfig.getClient())
                        .grant_type(keycloakConfig.getGrantType())
                        .username(username)
                        .password(password)
                        .build();

        return login(loginRequest);
    }

    public ResponseEntity signup(String email, String pass) {
        String adminToken = adminLogin().getBody().accessToken();
        KeycloackCredentials credentials =
                KeycloackCredentials.builder()
                        .value(pass)
                        .temporary(false)
                        .type("password")
                        .build();

        KeycloackSignupRequest signupRequest =
                KeycloackSignupRequest
                        .builder()
                        .email(email)
                        .credentials(Arrays.asList(credentials))
                        .enabled(true)
                        .build();
        return keycloackClient.registerUser(signupRequest, "Bearer " + adminToken);
    }



    private ResponseEntity<AuthResponse> adminLogin() {
        KeycloackAdminTokenRequest adminTokenRequest =
                KeycloackAdminTokenRequest
                        .builder()
                        .client_id("admin-cli")
                        .client_secret(keycloakConfig.getAdminSecret())
                        .grant_type("client_credentials")
                        .build();
        return keycloackClient.getCliToken(adminTokenRequest);
    }
}
