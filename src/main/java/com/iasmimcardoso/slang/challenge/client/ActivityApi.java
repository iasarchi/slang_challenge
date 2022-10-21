package com.iasmimcardoso.slang.challenge.client;

import com.iasmimcardoso.slang.challenge.dto.RequestActivityDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "ActivityApi", url = "https://api.slangapp.com/challenges/v1/activities")
public interface ActivityApi {
    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    RequestActivityDTO getAllActivities(@RequestHeader("Authorization") String authorization );
}
