package com.agenatech.solutions.iamaie2etests;

import com.agenatech.solutions.iamaie2etests.payload.request.Skill;
import com.agenatech.solutions.iamaie2etests.payload.response.EmbeddedProfilesResponseRoot;
import com.agenatech.solutions.iamaie2etests.service.DataManager;
import com.agenatech.solutions.iamaie2etests.service.GatewayService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
@Slf4j
class AdminPositiveTests {
	@Autowired
	private GatewayService gatewayService;
	@Autowired
	private DataManager dataManager;

	@Value("${test.test-user-id}")
	private String DEFAULT_USER_ID;

	@Value("${auth-server.iamai-admin-account}")
	private String ADMIN_EMAIL;




	@Test
	public void skillsSearch(){
		Skill generatedSkill = dataManager.generateSkill(UUID.randomUUID());
		gatewayService.addSkills(Arrays.asList(generatedSkill), DEFAULT_USER_ID);

		EmbeddedProfilesResponseRoot profilesResponseRoot =
				gatewayService.searchSkillsAsAdmin(ADMIN_EMAIL, Arrays.asList(generatedSkill.getName()), Arrays.asList(generatedSkill.getLevel()));

		assertTrue(profilesResponseRoot.get_embedded().getProfiles().get(0).get_links().getSelf().get("href").contains(DEFAULT_USER_ID));
	}

}
