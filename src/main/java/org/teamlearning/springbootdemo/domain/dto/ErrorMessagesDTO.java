package org.teamlearning.springbootdemo.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessagesDTO {
    @JsonProperty("error")
    private String error = null;

    @JsonProperty("code")
    private String code = null;

}

