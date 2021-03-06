package org.teamlearning.springbootdemo.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignTaskDTO {
    @JsonProperty("taskId")
    private Long taskId;

    @JsonProperty("userId")
    private String userId;
}