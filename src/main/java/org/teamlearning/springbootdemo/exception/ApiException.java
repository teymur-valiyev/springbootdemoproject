package org.teamlearning.springbootdemo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.teamlearning.springbootdemo.dto.ErrorMessagesDTO;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiException extends RuntimeException {

    private String title;
    private HttpStatus status;
    private List<ErrorMessagesDTO> messages;

    ApiException(List<ErrorMessagesDTO> messages) {
        this.messages = messages;
    }


}