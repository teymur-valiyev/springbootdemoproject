package org.teamlearning.springbootdemo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskResponseDTOs {
    List<TaskResponseDTO> taskResponseDTOS;
}

