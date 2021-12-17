package org.mentalizr.contentManager.validator;

import org.mentalizr.contentManager.Program;
import org.mentalizr.contentManager.fileHierarchy.levels.contentRoot.MdpDir;

import java.util.ArrayList;
import java.util.List;

public abstract class Validator {

    protected final Program program;
    protected final MdpDir mdpDir;
    protected List<ValidationError> validationErrors;

    public Validator(Program program) {
        this.program = program;
        this.mdpDir = program.getProgramDir().getMdpDir();
        this.validationErrors = new ArrayList<>();
        validate();
    }

    public ValidationResult getValidationResult() {
        return new ValidationResult(this.validationErrors);
    }

    protected abstract void validate();

}
