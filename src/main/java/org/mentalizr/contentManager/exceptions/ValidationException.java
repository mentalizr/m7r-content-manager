package org.mentalizr.contentManager.exceptions;

import org.mentalizr.contentManager.validator.ValidationError;

import java.util.Collections;
import java.util.List;

import static de.arthurpicht.utils.core.assertion.MethodPreconditions.assertArgumentNotNull;

public class ValidationException extends ContentManagerException {

    private final List<ValidationError> validationErrorList;

    public ValidationException(List<ValidationError> validationErrors) {
        super("Program validation failed.");
        assertArgumentNotNull("validationErrors", validationErrors);
        if (validationErrors.isEmpty()) throw new IllegalArgumentException("Parameter 'validationErrors' is empty.");
        this.validationErrorList = Collections.unmodifiableList(validationErrors);
    }

    public List<ValidationError> getValidationErrorList() {
        return validationErrorList;
    }

}
