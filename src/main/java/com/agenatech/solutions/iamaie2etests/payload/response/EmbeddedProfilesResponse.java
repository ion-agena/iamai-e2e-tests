package com.agenatech.solutions.iamaie2etests.payload.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class EmbeddedProfilesResponse {
    private List<UserProfile> profiles;
}
