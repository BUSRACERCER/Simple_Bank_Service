package com.works.bankservice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@ControllerAdvice
public class GlobalRestExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());

        // Get all errors
        List<String> validationErrors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getField() + " -> " + x.getDefaultMessage())
                .collect(Collectors.toList());

        body.put("errors", validationErrors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);

    }

}
