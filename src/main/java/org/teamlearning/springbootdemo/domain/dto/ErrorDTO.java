package org.teamlearning.springbootdemo.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import java.util.List;

@Data
@Builder
public class ErrorDTO {
    @JsonProperty("title")
    private String title = null;

    @JsonProperty("status")
    private Integer status = null;

    @JsonProperty("messages")
    @Valid
    private List<ErrorMessagesDTO> messages = null;

    @JsonProperty("traceId")
    private String traceId = null;

}

