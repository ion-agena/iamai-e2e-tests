package com.agenatech.solutions.iamaie2etests.payload.influencers;

import lombok.*;

import java.util.UUID;



@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor @AllArgsConstructor
@ToString @Builder

public class BriefInfluencerRelation  {

    UUID id;



    private Brief brief;

    private UUID briefId;


    private UUID influencerId;

    private String lifecycleStatus;
}
