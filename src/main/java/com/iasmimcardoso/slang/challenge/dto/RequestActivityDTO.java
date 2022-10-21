package com.iasmimcardoso.slang.challenge.dto;

import com.iasmimcardoso.slang.challenge.model.Activity;
import lombok.Data;

import java.util.List;
@Data
public class RequestActivityDTO {
    private List<Activity> activities;
}
