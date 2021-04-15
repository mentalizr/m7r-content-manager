package org.mentalizr.contentManager.fileHierarchy.levels.module;

import de.arthurpicht.configuration.Configuration;
import de.arthurpicht.configuration.ConfigurationFactory;
import de.arthurpicht.configuration.ConfigurationFileNotFoundException;
import de.arthurpicht.utils.core.strings.Strings;
import org.mentalizr.contentManager.exceptions.ContentManagerException;

import java.io.File;
import java.io.IOException;

public class ModuleConf {

    private final String name;

    public ModuleConf(File programConfFile) throws ContentManagerException {
        ConfigurationFactory configurationFactory = new ConfigurationFactory();
        try {
            configurationFactory.addConfigurationFileFromFilesystem(programConfFile);
            Configuration configuration = configurationFactory.getConfiguration();

            this.name = configuration.getString("name");
            if (Strings.isUnspecified(this.name))
                throw new ContentManagerException("Parameter 'name' not set in configuration 'module.conf'.");

        } catch (ConfigurationFileNotFoundException | IOException e) {
            throw new ContentManagerException(e);
        }
    }

    public String getName() {
        return name;
    }
}
