package com.iasmimcardoso.slang.challenge.model;

import lombok.Data;

import java.util.Date;

@Data
public class Activity {
    private int id;
    private String user_id;
    private Date answered_at;
    private Date first_seen_at;
}
