package com.iasmimcardoso.slang.challenge.controller;

import com.iasmimcardoso.slang.challenge.client.ActivityApi;
import com.iasmimcardoso.slang.challenge.dto.RequestActivityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActivityController {
    @Autowired
    ActivityApi activityApi;
    @GetMapping
    public RequestActivityDTO getActivities(){
        return activityApi.getAllActivities("Basic MTIxOkFNQmJsNVQxYkN0SDh0SVVaRk1MWDNvaDVuMkoxcE4ySHZFS0krdUxGUDQ9");
    }
}
