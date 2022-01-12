package com.agenatech.solutions.iamaie2etests.payload.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class EmbeddedSkillsResponseRoot {
    private EmbeddedSkillsResponse _embedded;
}
