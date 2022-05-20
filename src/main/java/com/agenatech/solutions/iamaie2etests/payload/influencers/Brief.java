package com.agenatech.solutions.iamaie2etests.payload.influencers;

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
public class Brief  {

    UUID id;

    private String title;


    private Set<BriefInfluencerRelation> influencers;

    private long priceAmount;
    private String currency;

    private String avatarUrl;

    private Set<UUID> brands;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endDate;

    private String platform;

    private boolean isForEveryone;
}
