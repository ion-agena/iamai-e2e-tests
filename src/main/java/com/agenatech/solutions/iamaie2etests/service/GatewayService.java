package com.agenatech.solutions.iamaie2etests.service;


import com.agenatech.solutions.iamaie2etests.client.ApiGwClient;
import com.agenatech.solutions.iamaie2etests.payload.request.Skill;
import com.agenatech.solutions.iamaie2etests.payload.response.EmbeddedSkillsResponseRoot;
import com.agenatech.solutions.iamaie2etests.payload.response.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.agenatech.solutions.iamaie2etests.config.Constants.DEFAULT_PASSWORD;

@Service
public class GatewayService {
    @Autowired
    private KeycloakService keycloakService;
    @Autowired
    private ApiGwClient apiGwClient;


    public UserProfile getDefaultProfile(){
        String bearerToken = keycloakService.defaultLogin().accessToken();
        return apiGwClient.getMyProfile("Bearer "  + bearerToken);
    }
    public UserProfile getMyProfile(String email){
        return apiGwClient.getMyProfile(getBearer(email));
    }

    public UserProfile getProfileById(String myEmail, String profileId){
        return apiGwClient.getProfileById(getBearer(myEmail), profileId);
    }

    public UserProfile putProfile(String email, UserProfile profile){
        return apiGwClient.putProfile(getBearer(email), profile);
    }

    public UserProfile putProfileById(String email, String profileId, UserProfile profile){
        return apiGwClient.putProfileById(getBearer(email), profileId, profile);
    }

    public void deleteMyProfile(String email){
         apiGwClient.deleteMyProfile(getBearer(email));
    }

    public UserProfile patchProfile(String email, Map attrs){
        return apiGwClient.patchProfile(getBearer(email), attrs);
    }

    public UserProfile patchProfileById(String email, String profileId, Map attrs){
        return apiGwClient.patchProfileById(getBearer(email), profileId, attrs);
    }

    public void addSkills(List<Skill> skills, String id){
        apiGwClient.addSkills(getDefaultBearer(), id, skills);
    }

    public EmbeddedSkillsResponseRoot getSkills(String id){
        return apiGwClient.getSkills(getDefaultBearer(), id);
    }



    private String getDefaultBearer(){
        return  "Bearer "  + keycloakService.defaultLogin().accessToken();
    }

    public String getBearer(String email){
        return  "Bearer "  + keycloakService.login(email, DEFAULT_PASSWORD).accessToken();
    }

}
