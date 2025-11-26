package labs.aui.exceptions;

import java.util.UUID;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resourceName, UUID resourceId) {
        super(resourceName + " with ID " + resourceId + " not found.");
    }
}
