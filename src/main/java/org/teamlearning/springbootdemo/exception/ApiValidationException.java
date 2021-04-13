package org.teamlearning.springbootdemo.exception;

import lombok.AllArgsConstructor;
import org.teamlearning.springbootdemo.dto.ErrorMessagesDTO;

import java.util.List;

@AllArgsConstructor
public class ApiValidationException extends ApiException {
    public ApiValidationException(List<ErrorMessagesDTO> message) {
        super(message);
    }
}


