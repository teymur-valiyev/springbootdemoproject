package org.teamlearning.springbootdemo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.teamlearning.springbootdemo.dto.ErrorDTO;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiErrorException extends RuntimeException {
    private ErrorDTO appError;
}
