package org.ai.aicopilotforapi.common.exception;

/** Thrown for forbidden access. */
public class ForbiddenException extends AppException {
    public ForbiddenException(String message) {
        super(403, message);
    }
}