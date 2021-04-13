package org.teamlearning.springbootdemo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.teamlearning.springbootdemo.enums.StatusEnum;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskUpdateDTO   {
  @JsonProperty("title")
  private String title ;

  @JsonProperty("description")
  private String description ;

  @JsonProperty("deadline")
  private String deadline ;

  @JsonProperty("status")
  private StatusEnum status ;

}

