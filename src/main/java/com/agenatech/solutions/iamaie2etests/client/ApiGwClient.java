package com.agenatech.solutions.iamaie2etests.client;

import com.agenatech.solutions.iamaie2etests.payload.influencers.Brief;
import com.agenatech.solutions.iamaie2etests.payload.influencers.BriefInfluencerRelation;
import com.agenatech.solutions.iamaie2etests.payload.request.Skill;
import com.agenatech.solutions.iamaie2etests.payload.response.EmbeddedProfilesResponseRoot;
import com.agenatech.solutions.iamaie2etests.payload.response.EmbeddedSkillsResponseRoot;
import com.agenatech.solutions.iamaie2etests.payload.response.UserProfile;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@FeignClient(url = "${api-gw.url}", value = "${api-gw.url}")
@Service
public interface ApiGwClient {

    @GetMapping(value = "${profiles.profiles-me-url}", consumes = MediaType.APPLICATION_JSON_VALUE)
    UserProfile getMyProfile(@RequestHeader("Authorization") String token);

    @GetMapping(value = "${profiles.profiles-url}/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    UserProfile getProfileById(@RequestHeader("Authorization") String token, @PathVariable("id") String id);


    @PutMapping(value = "${profiles.profiles-me-url}", consumes = MediaType.APPLICATION_JSON_VALUE)
    UserProfile putProfile(@RequestHeader("Authorization") String token, UserProfile profile);

    @DeleteMapping(value = "${profiles.profiles-me-url}", consumes = MediaType.APPLICATION_JSON_VALUE)
    UserProfile deleteMyProfile(@RequestHeader("Authorization") String token);

    @PutMapping(value = "${profiles.profiles-url}/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    UserProfile putProfileById(@RequestHeader("Authorization") String token, @PathVariable("id") String id, UserProfile profile);

    @PatchMapping(value = "${profiles.profiles-me-url}", consumes = MediaType.APPLICATION_JSON_VALUE)
    UserProfile patchProfile(@RequestHeader("Authorization") String token, Map attrs);

    @PatchMapping(value = "${profiles.profiles-url}/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    UserProfile patchProfileById(@RequestHeader("Authorization") String token, @PathVariable("id") String id, Map attrs);

    @PostMapping(value = "${profiles.profiles-url}/{id}/skills", consumes = MediaType.APPLICATION_JSON_VALUE)
    void addSkills(@RequestHeader("Authorization") String token, @PathVariable("id") String id, List<Skill> skills);

    @GetMapping(value = "${profiles.profiles-url}/{id}/skills", consumes = MediaType.APPLICATION_JSON_VALUE)
    EmbeddedSkillsResponseRoot getSkills(@RequestHeader("Authorization") String token, @PathVariable("id") String id);

    @GetMapping(value = "${profiles.profiles-url}/search/skills", consumes = MediaType.APPLICATION_JSON_VALUE)
    EmbeddedProfilesResponseRoot searchSkills(@RequestHeader("Authorization") String token, @RequestParam List<String> skills, @RequestParam List<String> skillLevels, @RequestParam String projection);






//    influencers

    @PostMapping(value = "${gmf.brief-url}")
    Brief createBrief(@RequestHeader("Authorization") String token, Brief brief);

    @PostMapping(value = "${gmf.send-brief-url}")
    BriefInfluencerRelation sendBrief(@RequestHeader("Authorization") String token, @PathVariable UUID briefId, BriefInfluencerRelation relation);
}
