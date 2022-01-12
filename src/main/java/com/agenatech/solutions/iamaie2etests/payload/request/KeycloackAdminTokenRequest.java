package com.agenatech.solutions.iamaie2etests.payload.request;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KeycloackAdminTokenRequest {
    private String client_id;
    private String grant_type;
    private String client_secret;
}
