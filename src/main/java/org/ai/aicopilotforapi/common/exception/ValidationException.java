package org.ai.aicopilotforapi.common.exception;

import java.util.List;

/** Thrown when input validation fails. */
public class ValidationException extends AppException {
    public ValidationException(List<String> validationErrors) {
        super(400, "Validation failed", validationErrors);
    }
}