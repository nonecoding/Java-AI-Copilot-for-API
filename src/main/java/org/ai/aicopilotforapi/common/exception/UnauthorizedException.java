package org.ai.aicopilotforapi.common.exception;

/**
 * Thrown for authentication or authorization failures.
 */
public class UnauthorizedException extends AppException {
    public UnauthorizedException(String message) {
        super(401, message);
    }
}