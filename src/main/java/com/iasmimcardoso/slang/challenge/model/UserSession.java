package com.iasmimcardoso.slang.challenge.model;

import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
public class UserSession {
   private Date started_at;
   private Date ended_at;
   private List<Integer> activity_ids;
   private double duration_seconds;
}
