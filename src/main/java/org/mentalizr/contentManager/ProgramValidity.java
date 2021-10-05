package org.mentalizr.contentManager;

import org.mentalizr.contentManager.exceptions.ContentManagerException;
import org.mentalizr.contentManager.programStructure.Module;
import org.mentalizr.contentManager.programStructure.*;

import java.util.List;

public class ProgramValidity {

    public static void check(Program program) throws ContentManagerException {
        ProgramStructure programStructure = program.asProgramStructure();
        checkExerciseAndFeedbackMarks(programStructure);
        checkForNonEmptyStructures(programStructure);
    }

    private static void checkForNonEmptyStructures(ProgramStructure programStructure) throws ContentManagerException {
        if (!ProgramStructures.hasModules(programStructure))
            throw new ContentManagerException(getExceptionMessage(programStructure, "Program has no modules"));
        for (Module module : programStructure.getModules()) {
            if (!Modules.hasSubmodules(module))
                throw new ContentManagerException(
                        getExceptionMessage(programStructure, "Module [" + module.getId() + "] has no submodules."));
            for (Submodule submodule : module.getSubmodules()) {
                if (!Submodules.hasSteps(submodule))
                    throw new ContentManagerException(
                            getExceptionMessage(programStructure, "Submodule [" + submodule.getId() + "] has no steps."));
            }
        }
    }

    private static void checkExerciseAndFeedbackMarks(ProgramStructure programStructure) throws ContentManagerException {
        List<Step> stepList = ProgramStructures.stepList(programStructure);
        for (int i = 0; i < stepList.size(); i++) {
            Step currentStep = stepList.get(i);
            if (currentStep.isFeedback()) {
                if (i == 0)
                    throw new ContentManagerException(getExceptionMessage(
                            programStructure, "First step [" + currentStep.getId() + "] cannot be marked as feedback.")
                    );
                Step previousStep = stepList.get(i - 1);
                if (!previousStep.isExercise())
                    throw new ContentManagerException(getExceptionMessage(
                            programStructure, "Feedback step [" + currentStep.getId() + "] has no preceding exercise step.")
                    );
            }
        }
    }

    private static String getExceptionMessage(ProgramStructure programStructure, String message) {
        return getMessagePrefix(programStructure.getId()) + message;
    }

    private static String getMessagePrefix(String programId) {
        return "Validity check failed for program [" + programId + "]: ";
    }
}
