package com.agenatech.solutions.iamaie2etests.utils;

import com.agenatech.solutions.iamaie2etests.payload.response.UserProfile;
import org.springframework.stereotype.Service;

@Service
public class UriUtils {

    public String getIdFromLink(String link){
        return link.substring(link.lastIndexOf("/") +1);
    }

    public String getSelfLinkFromUser(UserProfile profile){
        return profile.get_links().getSelf().get("href");
    }

}
