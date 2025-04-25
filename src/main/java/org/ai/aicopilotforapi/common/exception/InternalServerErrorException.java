package org.ai.aicopilotforapi.common.exception;

/**
 * Thrown for generic server errors.
 */
public class InternalServerErrorException extends AppException {
    public InternalServerErrorException(String message, Throwable cause) {
        super(500, message, cause);
    }
}