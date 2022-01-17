package com.agenatech.solutions.iamaie2etests;

import com.agenatech.solutions.iamaie2etests.payload.request.Skill;
import com.agenatech.solutions.iamaie2etests.service.DataManager;
import com.agenatech.solutions.iamaie2etests.service.GatewayService;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.catchThrowable;

@SpringBootTest
@ActiveProfiles("test")
@Slf4j
class AdminNegativeTests {
	@Autowired
	private GatewayService gatewayService;
	@Autowired
	private DataManager dataManager;


	@Value("${test.test-user-id}")
	private String DEFAULT_USER_ID;



	@Test
	public void useAdminSearch(){
		Skill generatedSkill = dataManager.generateSkill(UUID.randomUUID());
		gatewayService.addSkills(Arrays.asList(generatedSkill), DEFAULT_USER_ID);

		Throwable thrown = catchThrowable(() -> gatewayService.searchSkills(Arrays.asList(generatedSkill.getName()), Arrays.asList(generatedSkill.getLevel())));

		assertThat(thrown)
				.isInstanceOf(FeignException.Forbidden.class);
	}

}
