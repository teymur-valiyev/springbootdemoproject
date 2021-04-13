package org.teamlearning.springbootdemo.domain.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.teamlearning.springbootdemo.domain.enums.StatusEnum;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDTO   {
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

