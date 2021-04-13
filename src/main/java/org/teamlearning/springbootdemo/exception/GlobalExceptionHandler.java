package org.teamlearning.springbootdemo.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.teamlearning.springbootdemo.domain.dto.ErrorDTO;
import org.teamlearning.springbootdemo.domain.dto.ErrorMessagesDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final ErrorAttributes errorAttributes;

    @Autowired
    private Tracer tracer;

    public GlobalExceptionHandler(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @ExceptionHandler({ApiErrorException.class})
    public final ResponseEntity<Object> handleApiException(ApiErrorException ex) {
        return new ResponseEntity<>(ex.getAppError(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ApiException.class})
    public final ResponseEntity<Object> handleApiException(ApiException ex) {
        ErrorDTO errorDTO = ErrorDTO.builder()
                .title(ex.getTitle())
                .status(ex.getStatus().value())
                .traceId(getCurrentTraceId())
                .messages(ex.getMessages())
                .build();
        return new ResponseEntity<>(errorDTO, new HttpHeaders(), ex.getStatus());
    }

    @ExceptionHandler({ApiValidationException.class})
    private ResponseEntity<Object> handleApiValidationException(ApiValidationException ex) {
        List<ErrorMessagesDTO> messages = ex.getMessages();

        ErrorDTO errorDTO = ErrorDTO.builder()
                .title(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .status(HttpStatus.BAD_REQUEST.value())
                .traceId(getCurrentTraceId())
                .messages(messages)
                .build();
        return new ResponseEntity<>(errorDTO, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ApiInternalServerErrorException.class})
    private ResponseEntity<Object> handleApiValidationException(ApiInternalServerErrorException ex) {
        ErrorDTO errorDTO = ErrorDTO.builder()
                .title(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .traceId(getCurrentTraceId())
                .messages(new ArrayList<ErrorMessagesDTO>() {
                })
                .build();
        return new ResponseEntity<>(errorDTO, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    private ResponseEntity<Object> handleUnknownException(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        HttpHeaders headers = new HttpHeaders();
        Map<String, Object> errorAttributes = this.errorAttributes.getErrorAttributes(request, false);
        String message = (String) errorAttributes.get("message");

        ErrorDTO errorDTO = ErrorDTO.builder()
                .title(status.getReasonPhrase())
                .status(status.value())
                .traceId(getCurrentTraceId())
                .messages(new ArrayList<ErrorMessagesDTO>() {
                })
                .build();

        return new ResponseEntity<>(errorDTO, headers, status);
    }


    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = ex.getMessage();
        ErrorDTO errorDTO = ErrorDTO.builder()
                .title(message)
                .status(status.value())
                .traceId(getCurrentTraceId())
                .messages(new ArrayList<ErrorMessagesDTO>() {
                })
                .build();

        return new ResponseEntity<>(errorDTO, headers, status);
    }

    private String getDetailFromInvalidParams(List<ErrorMessagesDTO> invalidParams) {
        String detail = invalidParams.stream()
                .map(invalidParam -> invalidParam.getError() + " " + invalidParam.getCode())
                .reduce("", (next, current) -> current + "," + next);
        return detail.substring(0, detail.length() - 1);
    }


    private String getCurrentTraceId() {
        return Objects.requireNonNull(tracer.currentSpan()).context().traceId();
    }
}
