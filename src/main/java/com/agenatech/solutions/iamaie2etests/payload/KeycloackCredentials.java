package com.agenatech.solutions.iamaie2etests.payload;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KeycloackCredentials {
    private String type;
    private String value;
    boolean temporary;
}
