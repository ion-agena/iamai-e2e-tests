package com.agenatech.solutions.iamaie2etests.payload.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Skill {
    @EqualsAndHashCode.Include
    private String name;
    private String level;
}
