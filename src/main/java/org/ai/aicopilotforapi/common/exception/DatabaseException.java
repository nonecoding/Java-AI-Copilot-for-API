package org.ai.aicopilotforapi.common.exception;

/**
 * Thrown for any database-related issues.
 */
public class DatabaseException extends AppException {
    public DatabaseException(String message, Throwable cause) {
        super(500, message, cause);
    }
}