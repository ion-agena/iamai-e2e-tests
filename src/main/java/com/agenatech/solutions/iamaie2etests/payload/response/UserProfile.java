package com.agenatech.solutions.iamaie2etests.payload.response;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor @Builder @ToString
public class UserProfile {


    private String email;

    private String firstName;

    private String lastName;

    private String avatarUrl;

    private HalLinks _links;


}
