package com.agenatech.solutions.iamaie2etests.influencers.data;


import com.agenatech.solutions.iamaie2etests.payload.influencers.Brief;
import com.agenatech.solutions.iamaie2etests.payload.influencers.BriefInfluencerRelation;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Service
public class TestDataManager {

    public Brief generateBrief(Set<UUID> brands){
        return Brief.builder()
                .title("testBrief")
                .priceAmount(20)
                .currency("KR")
                .platform("FACEBOOK")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .brands(brands)
                .build();
    }

    public Brief generatePublicBrief(Set<UUID> brands){
        Brief brief = generateBrief(brands);
        brief.setForEveryone(true);
        return brief;
    }


//    public Pitch generatePitch(UUID influencerId){
//        return Pitch.builder()
//                .title("testBrief")
//                .priceAmount(20)
//                .currency(Currency.KR)
//                .platform(SocialId.FACEBOOK)
//                .influencer(influencerId)
//                .startDate(LocalDate.now())
//                .endDate(LocalDate.now())
//                .build();
//    }

//    public Campaign generatePitchCampaign(UUID influencerId, UUID pitchId){
//        return  Campaign.builder()
//                .pitchId(pitchId)
//                .influencer(influencerId)
//                .isPitchBased(true)
//                .lifecycleStatus(LifecycleStatus.NEW)
//                .build();
//    }
//
    public BriefInfluencerRelation generateBriefInfluencerRel(UUID influencerId){
        return BriefInfluencerRelation.builder()
                .influencerId(influencerId)
                .lifecycleStatus("REQUESTED")
                .build();
    }


//    public List<PitchBrandRelation> generatePitchBrandRelPayload(UUID brandId, UUID pitchId){
//        return List.of(PitchBrandRelation.builder().brandId(brandId).pitchId(pitchId).lifecycleStatus(LifecycleStatus.REQUESTED).build());
//    }
//
//
//    public Description generateDescription(UUID id){
//        return Description.builder().value("description for " + id).build();
//    }
//
//    public Rating generateCampaignRating(){
//        return Rating.builder()
//                .rating(Byte.valueOf("1"))
//                .feedback("feedback " + UUID.randomUUID())
//                .build();
//    }
//
//    public Comment generateComment(String text){
//        return Comment.builder().textValue(text + "kjwedjklwenfwe jkweld").build();
//    }


}
