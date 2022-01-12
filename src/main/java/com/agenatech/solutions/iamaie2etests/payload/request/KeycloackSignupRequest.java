package com.agenatech.solutions.iamaie2etests.payload.request;

import com.agenatech.solutions.iamaie2etests.payload.KeycloackCredentials;
import lombok.*;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KeycloackSignupRequest {
    private String email;
    private boolean enabled;
    private List<KeycloackCredentials> credentials;
}
