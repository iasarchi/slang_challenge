package com.iasmimcardoso.slang.challenge.controller;

import com.iasmimcardoso.slang.challenge.model.UserSession;
import com.iasmimcardoso.slang.challenge.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class ActivityController {
    @Autowired
    ActivityService activityService;
    @GetMapping
    public HashMap<String, List<UserSession>> getActivities(){
        return activityService.process();
    }
}
