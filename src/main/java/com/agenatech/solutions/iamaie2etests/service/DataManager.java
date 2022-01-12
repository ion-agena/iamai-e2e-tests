package com.agenatech.solutions.iamaie2etests.service;


import com.agenatech.solutions.iamaie2etests.payload.request.Skill;
import com.agenatech.solutions.iamaie2etests.payload.response.UserProfile;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DataManager {
    public UserProfile generateUserProfile(String email){
        String id = UUID.randomUUID().toString();
        return UserProfile.builder()
                .email(email)
                .firstName("fn " + id)
                .lastName("ln " + id)
                .avatarUrl(id)
                .build();
    }

    public Skill generateSkill(UUID id){
        return Skill.builder()
                .name("Skill" + id)
                .level("beginner")
                .build();
    }
}
