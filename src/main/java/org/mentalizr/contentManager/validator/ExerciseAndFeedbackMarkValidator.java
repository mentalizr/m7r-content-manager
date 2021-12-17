package org.mentalizr.contentManager.validator;

import org.mentalizr.contentManager.Program;
import org.mentalizr.contentManager.fileHierarchy.levels.contentFile.MdpFile;

import java.util.List;

public class ExerciseAndFeedbackMarkValidator extends Validator {

    public ExerciseAndFeedbackMarkValidator(Program program) {
        super(program);
    }

    @Override
    protected void validate() {
        List<MdpFile> contentFiles = mdpDir.getContentFiles();
        for (int i = 0; i < contentFiles.size(); i++) {
            MdpFile mdpFile = contentFiles.get(i);
            if (mdpFile.isFeedback()) {
                if (i == 0)
                    validationErrors.add(new ValidationError(program.getName(),
                            "First step [" + mdpFile.getId() + "] cannot be marked as feedback."));
                if (i > 0) {
                    MdpFile previousStep = contentFiles.get(i - 1);
                    if (!previousStep.isExercise())
                        validationErrors.add(new ValidationError(program.getName(),
                                "Feedback step [" + mdpFile.getId() + "] has no preceding exercise step."));
                }
            }
        }

    }

}
