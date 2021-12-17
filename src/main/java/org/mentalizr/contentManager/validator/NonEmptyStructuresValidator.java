package org.mentalizr.contentManager.validator;

import org.mentalizr.contentManager.Program;
import org.mentalizr.contentManager.fileHierarchy.levels.module.ModuleDirMdp;
import org.mentalizr.contentManager.fileHierarchy.levels.submodule.SubmoduleDirMdp;
import org.mentalizr.contentManager.helper.Nio2Helper;

import java.util.List;

public class NonEmptyStructuresValidator extends Validator {

    public NonEmptyStructuresValidator(Program program) {
        super(program);
    }

    @Override
    protected void validate() {

        if (!Nio2Helper.isExistingRegularFile(mdpDir.asPath().resolve("program.conf")))
            validationErrors.add(new ValidationError(this.program.getName(), "program.conf missing"));

        List<String> moduleDirNames = mdpDir.getModuleDirNames();
        if (moduleDirNames.isEmpty())
            validationErrors.add(new ValidationError(this.program.getName(), "Program has no modules"));

        for (String moduleDirName : moduleDirNames) {
            ModuleDirMdp moduleDirMdp = mdpDir.getModuleDir(moduleDirName);

            List<String> submoduleDirNames = moduleDirMdp.getSubmoduleDirNames();
            if (submoduleDirNames.isEmpty())
                validationErrors.add(new ValidationError(this.program.getName(),
                        "Module [" + moduleDirMdp.getId() + "] has no submodules."));

            for (String submoduleDirName : submoduleDirNames) {
                SubmoduleDirMdp submoduleDirMdp = moduleDirMdp.getSubmoduleDir(submoduleDirName);
                List<String> stepNames = submoduleDirMdp.getStepFileNames();
                if (stepNames.isEmpty())
                    validationErrors.add(new ValidationError(this.program.getName(),
                            "Submodule [" + submoduleDirMdp.getId() + "] has no steps."));
            }
        }
    }

}
