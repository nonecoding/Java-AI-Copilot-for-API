package org.ai.aicopilotforapi.common.exception;

import java.util.Collections;
import java.util.List;

/**
 * A generic application exception that wraps business errors.
 */
public class AppException extends RuntimeException {
    private final int code;
    private final List<String> details;

    /**
     * Creates a new AppException.
     *
     * @param code    a numeric error code
     * @param message human-readable message
     */
    public AppException(int code, String message) {
        this(code, message, null, Collections.emptyList());
    }

    public AppException(int code, String message,String data) {
        this(code, message, null, Collections.emptyList());
    }

    /**
     * Creates a new AppException with cause.
     *
     * @param code    numeric error code
     * @param message human-readable message
     * @param cause   root cause
     */
    public AppException(int code, String message, Throwable cause) {
        this(code, message, cause, Collections.emptyList());
    }

    /**
     * Creates a new AppException with details list.
     *
     * @param code    numeric error code
     * @param message human-readable message
     * @param details list of detailed error messages (e.g. field validation errors)
     */
    public AppException(int code, String message, List<String> details) {
        this(code, message, null, details);
    }

    /**
     * Full constructor.
     */
    public AppException(int code, String message, Throwable cause, List<String> details) {
        super(message, cause);
        this.code    = code;
        this.details = details;
    }

    public int getCode() {
        return code;
    }

    public List<String> getDetails() {
        return details;
    }
}
