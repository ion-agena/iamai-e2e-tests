package com.agenatech.solutions.iamaie2etests.payload.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class EmbeddedProfilesResponseRoot {
    private EmbeddedProfilesResponse _embedded;
}
