package org.teamlearning.springbootdemo.domain.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.teamlearning.springbootdemo.domain.enums.StatusEnum;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {

  @JsonProperty("id")
  private int id;

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("title")
  private String title;

  @JsonProperty("description")
  private String description;

  @JsonProperty("deadline")
  private String deadline;

  @JsonProperty("status")
  private StatusEnum status;

  @JsonProperty("userId")
  private String userId;

}

