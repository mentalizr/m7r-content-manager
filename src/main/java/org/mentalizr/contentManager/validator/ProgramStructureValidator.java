package org.mentalizr.contentManager.validator;

import org.mentalizr.contentManager.Program;
import org.mentalizr.contentManager.exceptions.ContentManagerException;
import org.mentalizr.contentManager.programStructure.Module;
import org.mentalizr.contentManager.programStructure.*;

import java.util.List;

@Deprecated
public class ProgramStructureValidator {

//    public static ValidationResult validate(Program program) throws ContentManagerException {
//        ProgramStructure programStructure = program.asProgramStructure();
//        ValidationResult result = new ValidationResult();
//        checkExerciseAndFeedbackMarks(programStructure, result);
//        checkForNonEmptyStructures(programStructure, result);
//        return result;
//    }
//
//    private static void checkForNonEmptyStructures(
//            ProgramStructure programStructure, ValidationResult result) {
//
//        if (!ProgramStructures.hasModules(programStructure))
//            result.add(new ValidationError(programStructure.getId(), "Program has no modules"));
//
//        for (Module module : programStructure.getModules()) {
//            if (!Modules.hasSubmodules(module))
//                result.add(new ValidationError(programStructure.getId(),
//                        "Module [" + module.getId() + "] has no submodules."));
//
//            for (Submodule submodule : module.getSubmodules()) {
//                if (!Submodules.hasSteps(submodule))
//                    result.add(new ValidationError(programStructure.getId(),
//                            "Submodule [" + submodule.getId() + "] has no steps."));
//            }
//        }
//    }
//
//    private static void checkExerciseAndFeedbackMarks(
//            ProgramStructure programStructure, ValidationResult result) throws ContentManagerException {
//
//        List<Step> stepList = ProgramStructures.stepList(programStructure);
//        for (int i = 0; i < stepList.size(); i++) {
//            Step currentStep = stepList.get(i);
//            if (currentStep.isFeedback()) {
//                if (i == 0)
//                    result.add(new ValidationError(programStructure.getId(),
//                            "First step [" + currentStep.getId() + "] cannot be marked as feedback."));
//                Step previousStep = stepList.get(i - 1);
//                if (!previousStep.isExercise())
//                    result.add(new ValidationError(programStructure.getId(),
//                            "Feedback step [" + currentStep.getId() + "] has no preceding exercise step."));
//            }
//        }
//    }

}
