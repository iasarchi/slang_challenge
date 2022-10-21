package com.iasmimcardoso.slang.challenge.service;

import com.iasmimcardoso.slang.challenge.client.ActivityApi;
import com.iasmimcardoso.slang.challenge.dto.RequestActivityDTO;
import com.iasmimcardoso.slang.challenge.model.Activity;
import com.iasmimcardoso.slang.challenge.model.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class ActivityService {
    @Autowired
    ActivityApi activityApi;

    public HashMap<String, List<UserSession>> process(){

        // Get all activites
        RequestActivityDTO allActivities = activityApi.getAllActivities("Basic MTIxOkFNQmJsNVQxYkN0SDh0SVVaRk1MWDNvaDVuMkoxcE4ySHZFS0krdUxGUDQ9");

        HashMap<String, List<Activity>> userActivities = new HashMap<>();
        HashMap<String, List<UserSession>> userSessions = new HashMap<>();

        // Separating all activities by user
        allActivities.getActivities().forEach(activity -> {
            if (userActivities.containsKey(activity.getUser_id())){
                userActivities.get(activity.getUser_id()).add(activity);
            }else {
                userActivities.put(activity.getUser_id(),new ArrayList<>(List.of(activity)));
            }
        });

        // Processing each user's activities
        for (Map.Entry<String, List<Activity>> entry : userActivities.entrySet()) {
            userSessions.put(entry.getKey(), new ArrayList<>());

            List<Activity> activities = entry.getValue();

            // Sorting activities by first_seen_at
            activities.sort(Comparator.comparing(Activity::getFirst_seen_at));

            Activity lastActivity = activities.get(0);

            UserSession userSession = new UserSession();
            userSession.setStarted_at(lastActivity.getFirst_seen_at());
            userSession.setEnded_at(lastActivity.getAnswered_at());
            userSession.setActivity_ids(new ArrayList<>());

            // Processing user's activities
            for(Activity activity: activities) {
                long timeDiff = ChronoUnit.MINUTES.between(lastActivity.getAnswered_at().toInstant(), activity.getFirst_seen_at().toInstant());

                // If time <= 5minutes add activity in actual session
                if(timeDiff <= 5) {
                    userSession.getActivity_ids().add(activity.getId());
                    userSession.setEnded_at(activity.getAnswered_at());
                } else {
                    // Else activity more than 5 minutes, add actual session in map
                    double activityDiff = ChronoUnit.SECONDS.between(userSession.getStarted_at().toInstant(), userSession.getEnded_at().toInstant());
                    userSession.setDuration_seconds(activityDiff);
                    userSessions.get(entry.getKey()).add(userSession);

                    // And create a new UserSession to actual activity
                    userSession = new UserSession();
                    userSession.setStarted_at(activity.getFirst_seen_at());
                    userSession.setEnded_at(activity.getAnswered_at());
                    userSession.setActivity_ids(new ArrayList<>());
                }
                lastActivity = activity;
            }
        }

        return userSessions;
    }


}
