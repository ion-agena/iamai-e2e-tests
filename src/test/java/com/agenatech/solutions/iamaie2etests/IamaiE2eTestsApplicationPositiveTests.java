package com.agenatech.solutions.iamaie2etests;

import com.agenatech.solutions.iamaie2etests.payload.request.Skill;
import com.agenatech.solutions.iamaie2etests.payload.response.EmbeddedSkillsResponseRoot;
import com.agenatech.solutions.iamaie2etests.payload.response.UserProfile;
import com.agenatech.solutions.iamaie2etests.service.DataManager;
import com.agenatech.solutions.iamaie2etests.service.GatewayService;
import com.agenatech.solutions.iamaie2etests.service.KeycloakService;
import com.agenatech.solutions.iamaie2etests.utils.UriUtils;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

import static com.agenatech.solutions.iamaie2etests.config.Constants.DEFAULT_PASSWORD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
class IamaiE2eTestsApplicationPositiveTests {
	@Autowired
	private KeycloakService keycloakService;
	@Autowired
	private GatewayService gatewayService;
	@Autowired
	private DataManager dataManager;
	@Autowired
	private UriUtils uriUtils;

	@Value("${test.test-user-id}")
	private String DEFAULT_USER_ID;


	@Test
	public void getMe(){
		UserProfile myProfile = gatewayService.getDefaultProfile();
		log.debug("------- profile {}", myProfile);

		assertTrue(DEFAULT_USER_ID.equals(uriUtils.getIdFromLink(uriUtils.getSelfLinkFromUser(myProfile))));
	}

	@Test
	public void putProfile(){
		String email = UUID.randomUUID() + "@mail.com";
		keycloakService.signup(email, DEFAULT_PASSWORD);
		UserProfile generatedProfile = dataManager.generateUserProfile(email);

		gatewayService.putProfile(email, generatedProfile);

		UserProfile retrievedProfile = gatewayService.getMyProfile(email);

		assertTrue(retrievedProfile.getAvatarUrl().equals(generatedProfile.getAvatarUrl()));
	}

	@Test
	public void deleteProfile(){
		String email = UUID.randomUUID() + "@mail.com";
		keycloakService.signup(email, DEFAULT_PASSWORD);
		UserProfile generatedProfile = dataManager.generateUserProfile(email);

		gatewayService.putProfile(email, generatedProfile);

		UserProfile retrievedProfile = gatewayService.getMyProfile(email);

		assertTrue(retrievedProfile.getAvatarUrl().equals(generatedProfile.getAvatarUrl()));

		log.debug("-----------continue");

		gatewayService.deleteMyProfile(email);

		Throwable thrown = catchThrowable(() -> gatewayService.getMyProfile(email));

		assertThat(thrown)
				.isInstanceOf(FeignException.NotFound.class);

	}

	@Test
	public void patchProfile(){
		String email = UUID.randomUUID() + "@mail.com";
		keycloakService.signup(email, DEFAULT_PASSWORD);
		UserProfile generatedProfile = dataManager.generateUserProfile(email);

		gatewayService.putProfile(email, generatedProfile);

		var newFieldValue = new HashMap<String, String>();
		String newValue = UUID.randomUUID().toString();
		newFieldValue.put("avatarUrl", newValue);

		UserProfile retrievedProfile = gatewayService.patchProfile(email, newFieldValue);

		assertTrue(retrievedProfile.getAvatarUrl().equals(newValue));
	}

	@Test
	public void addSkills(){
		Skill generatedSkill = dataManager.generateSkill(UUID.randomUUID());
		gatewayService.addSkills(Arrays.asList(generatedSkill), DEFAULT_USER_ID);
		EmbeddedSkillsResponseRoot skillsResponseRoot = gatewayService.getSkills(DEFAULT_USER_ID);

		assertTrue(skillsResponseRoot.get_embedded().getSkills().contains(generatedSkill));
	}

	@Test
	public void countSkills(){
		Skill generatedSkill = dataManager.generateSkill(UUID.randomUUID());
		gatewayService.addSkills(Arrays.asList(generatedSkill), DEFAULT_USER_ID);

		long skillsResult = gatewayService.countSkills(Arrays.asList(generatedSkill.getName()), Arrays.asList(generatedSkill.getLevel()));

		assertEquals(skillsResult, 1);
	}

}
