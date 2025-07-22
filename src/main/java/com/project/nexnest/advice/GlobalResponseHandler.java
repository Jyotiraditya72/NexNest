package com.project.nexnest.advice;

import lombok.*;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
@Builder
@ControllerAdvice
@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // Apply to all responses except when it's already ApiResponse or ApiError
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        // If body is already ApiResponse or ApiError, return as is
        if (body instanceof ApiResponse || body instanceof ApiError) {
            return body;
        }

        // Wrap other responses into ApiResponse
        return ApiResponse.builder()
                .status(HttpStatus.OK)
                .message("Success")
                .data(body)
                .build();
    }
}
