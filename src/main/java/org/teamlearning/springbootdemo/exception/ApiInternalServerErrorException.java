package org.teamlearning.springbootdemo.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class ApiInternalServerErrorException extends RuntimeException {

}

