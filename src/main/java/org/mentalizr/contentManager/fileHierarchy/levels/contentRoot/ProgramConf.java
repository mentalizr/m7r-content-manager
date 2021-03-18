package org.mentalizr.contentManager.fileHierarchy.levels.contentRoot;

import de.arthurpicht.configuration.Configuration;
import de.arthurpicht.configuration.ConfigurationFactory;
import de.arthurpicht.configuration.ConfigurationFileNotFoundException;
import de.arthurpicht.utils.core.strings.Strings;
import org.mentalizr.contentManager.exceptions.ProgramManagerException;

import java.io.File;
import java.io.IOException;

public class ProgramConf {

    private final String name;

    public ProgramConf(File programConfFile) throws ProgramManagerException {
        ConfigurationFactory configurationFactory = new ConfigurationFactory();
        try {
            configurationFactory.addConfigurationFileFromFilesystem(programConfFile);
            Configuration configuration = configurationFactory.getConfiguration();

            this.name = configuration.getString("name");
            if (Strings.isUnspecified(this.name))
                throw new ProgramManagerException("Parameter 'name' not set in configuration 'program.conf'.");

        } catch (ConfigurationFileNotFoundException | IOException e) {
            throw new ProgramManagerException(e);
        }
    }

    public String getName() {
        return name;
    }
}
