package com.agenatech.solutions.iamaie2etests.payload.influencers;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;


@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder @ToString(onlyExplicitlyIncluded = true)
public class Campaign {

    UUID id;

    private String title;


    private Brief brief;


    private UUID briefId;

//    private Pitch pitch;


//    private UUID pitchId;


    private boolean isPitchBased;

    private UUID influencer;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<UUID> brands;

    private String lifecycleStatus;

    private String contractUrl;

    private boolean isContractApproved;

    private String avatarUrl;

    private Set<Object> drafts;


    private Set<Object> statistics;

    private boolean isDraftApproved;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endDate;
}
