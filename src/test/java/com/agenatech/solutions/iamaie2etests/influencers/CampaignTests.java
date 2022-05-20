package com.agenatech.solutions.iamaie2etests.influencers;

import com.agenatech.solutions.iamaie2etests.influencers.data.TestDataManager;
import com.agenatech.solutions.iamaie2etests.payload.influencers.BriefInfluencerRelation;
import com.agenatech.solutions.iamaie2etests.service.GatewayService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
@ActiveProfiles("test")
@Slf4j
class CampaignTests {
    @Autowired
    private GatewayService gatewayService;
    @Autowired
    private TestDataManager testDataManager;

    @Test
    void createBriefCampaign() throws Exception{
//        UUID influencerId = UUID.randomUUID();
//        UUID brandId = UUID.randomUUID();
//        Brief brief = testTxManager.saveBriefInTx(testDataManager.generateBrief(Collections.singleton(brandId)));
//        BriefInfluencerRelation relation = testTxManager.saveBriefInfluencerRelationInTx(testDataManager.generateBriefInfluencerRel(brief.getId(), influencerId));
//
//        this.mockMvc.perform(post(CONTROLLER_URL_ROOT_PREFIX + "/commands/"+brandId+"/create-campaign-from-brief/" + relation.getId())
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.influencer").value(influencerId.toString()));

        UUID brandId = UUID.randomUUID();
//        Brief brief = gatewayService.postBrief(testDataManager.generateBrief(Set.of(brandId)));

        UUID briefId = UUID.fromString("292e0ca6-3241-44e9-a4bc-a7ab56ee4b12");
//        BriefInfluencerRelation relation = gatewayService.sendBrief(briefId, testDataManager.generateBriefInfluencerRel(UUID.randomUUID()));

        UUID rel = UUID.fromString("ed1505c7-bbb9-4680-a523-d0d41b30d0d4");
        assert (false);

    }

}
