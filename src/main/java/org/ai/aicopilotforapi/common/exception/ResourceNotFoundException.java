package org.ai.aicopilotforapi.common.exception;

import java.util.List;

/**
 * Thrown when a requested resource cannot be found.
 */
public class ResourceNotFoundException extends AppException {
    public ResourceNotFoundException(String resource, String id) {
        super(404, String.format("%s with id [%s] not found", resource, id));
    }
}






