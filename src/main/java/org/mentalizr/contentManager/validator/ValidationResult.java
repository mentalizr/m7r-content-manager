package org.mentalizr.contentManager.validator;

import java.util.List;

public class ValidationResult {

    private final List<ValidationError> validationErrorList;

    public ValidationResult(List<ValidationError> validationErrors) {
        this.validationErrorList = validationErrors;
    }

    public boolean isSuccess() {
        return this.validationErrorList.isEmpty();
    }

    public List<ValidationError> getValidationErrors() {
        return this.validationErrorList;
    }

}
