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
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
class IamaiE2eTestsApplicationNegativeTests {
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
	public void getOtherProfile(){
		UserProfile myProfile = gatewayService.getDefaultProfile();
		log.debug("------- profile {}", myProfile);

		String email = UUID.randomUUID() + "@mail.com";
		keycloakService.signup(email, DEFAULT_PASSWORD);

		Throwable thrown = catchThrowable(() -> gatewayService.getProfileById(email, DEFAULT_USER_ID) );

		assertThat(thrown)
				.isInstanceOf(FeignException.NotFound.class);
	}

	@Test
	public void putIncorrectProfileId(){
		String email = UUID.randomUUID() + "@mail.com";
		keycloakService.signup(email, DEFAULT_PASSWORD);
		UserProfile generatedProfile = dataManager.generateUserProfile(email);

		Throwable thrown = catchThrowable(() -> gatewayService.putProfileById(email, UUID.randomUUID().toString(), generatedProfile));

		assertThat(thrown)
				.isInstanceOf(FeignException.NotFound.class);
	}

	@Test
	public void patchOtherProfile(){
		String email = UUID.randomUUID() + "@mail.com";
		keycloakService.signup(email, DEFAULT_PASSWORD);
		UserProfile generatedProfile = dataManager.generateUserProfile(email);

		gatewayService.putProfile(email, generatedProfile);

		var newFieldValue = new HashMap<String, String>();
		String newValue = UUID.randomUUID().toString();
		newFieldValue.put("avatarUrl", newValue);

		Throwable thrown = catchThrowable(() ->  gatewayService.patchProfileById(email, DEFAULT_USER_ID, newFieldValue));
		assertThat(thrown)
				.isInstanceOf(FeignException.NotFound.class);
	}




}
