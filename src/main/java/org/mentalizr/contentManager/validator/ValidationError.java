package org.mentalizr.contentManager.validator;

public class ValidationError {

    private final String programId;
    private final String message;

    public ValidationError(String programId, String message) {
        this.programId = programId;
        this.message = message;
    }

    public String getProgramId() {
        return this.programId;
    }

    public String getMessage() {
        return this.message;
    }

}
