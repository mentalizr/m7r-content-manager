package org.mentalizr.contentManager.outdated.programManager;

import org.mentalizr.serviceObjects.frontend.program.*;
import org.mentalizr.serviceObjects.frontend.program.Module;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Deprecated
public class ProgramDebug {

    private static Logger logger = LoggerFactory.getLogger(ProgramDebug.class);

    public static void out(Program program) {

        if (program == null) {
            logger.debug("Program ist NULL.");
            return;
        }

        logger.debug("Program ID: " + program.getId() + " Name: " + program.getName());

        for (Module module : program.getModules()) {
            logger.debug("    Module ID: " + module.getId() + " Name: " + module.getName());
            for (Submodule submodule : module.getSubmodules()) {
                logger.debug("        Submodule ID: " + submodule.getId() + " Name: " + submodule.getName());
                for (Step step : submodule.getSteps()) {
                    logger.debug("            Step ID: " + step.getId() + " Step: " + step.getName());
                }
            }
        }

        for (Infotext infoText : program.getInfotexts()) {
            logger.debug("    Infotext ID: " + infoText.getId() + " Name: " + infoText.getName());
        }
    }

}
