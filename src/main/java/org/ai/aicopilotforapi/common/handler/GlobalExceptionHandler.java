package org.ai.aicopilotforapi.common.handler;

import org.ai.aicopilotforapi.common.config.ApiResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

/**
 * Centralized exception handling that returns ApiResult.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 400 – Missing or invalid request body
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResult<?> handleValidationErrors(MethodArgumentNotValidException ex) {
        StringBuilder sb = new StringBuilder();
        ex.getBindingResult().getFieldErrors().forEach(fe ->
                sb.append(fe.getField())
                        .append(": ")
                        .append(fe.getDefaultMessage())
                        .append("; ")
        );
        return ApiResult.<Object>failure(400, "Validation failed: " + sb.toString());
    }
    // :contentReference[oaicite:0]{index=0}

    // 400 – Invalid path or query parameter type
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ApiResult<?> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String msg = String.format("Parameter '%s' should be of type %s",
                ex.getName(),
                ex.getRequiredType().getSimpleName());
        return ApiResult.<Object>failure(400, msg);
    }
    // :contentReference[oaicite:1]{index=1}

    // 400 – Constraint violations (e.g. @RequestParam, @PathVariable)
    @ExceptionHandler(ConstraintViolationException.class)
    public ApiResult<?> handleConstraintViolation(ConstraintViolationException ex) {
        return ApiResult.<Object>failure(400, "Constraint violation: " + ex.getMessage());
    }
    // :contentReference[oaicite:2]{index=2}

    // 404 – No handler found for request
    @ExceptionHandler(NoHandlerFoundException.class)
    public ApiResult<?> handleNotFound(NoHandlerFoundException ex) {
        return ApiResult.<Object>failure(404,
                "No endpoint for " + ex.getHttpMethod() + " " + ex.getRequestURL());
    }
    // :contentReference[oaicite:3]{index=3}

    // 405 – Method not allowed
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ApiResult<?> handleMethodNotAllowed(HttpRequestMethodNotSupportedException ex) {
        return ApiResult.<Object>failure(405,
                ex.getMethod() + " not supported for this endpoint");
    }

    // 500 – Catch-all for unhandled exceptions
    @ExceptionHandler(Exception.class)
    public ApiResult<?> handleAllExceptions(Exception ex, HttpServletRequest request) {
        // Log stack trace for internal debugging
        ex.printStackTrace();
        return ApiResult.<Object>failure(500, "Internal server error");
    }
    // :contentReference[oaicite:4]{index=4}
}
