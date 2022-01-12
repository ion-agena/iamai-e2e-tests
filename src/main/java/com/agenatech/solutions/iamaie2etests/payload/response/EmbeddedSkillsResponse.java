package com.agenatech.solutions.iamaie2etests.payload.response;

import com.agenatech.solutions.iamaie2etests.payload.request.Skill;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class EmbeddedSkillsResponse {
    private List<Skill> skills;
}
