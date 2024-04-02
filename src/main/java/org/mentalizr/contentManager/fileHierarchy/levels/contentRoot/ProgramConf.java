package org.mentalizr.contentManager.fileHierarchy.levels.contentRoot;

import de.arthurpicht.configuration.Configuration;
import de.arthurpicht.configuration.ConfigurationFactory;
import de.arthurpicht.configuration.ConfigurationFileNotFoundException;
import de.arthurpicht.utils.core.strings.Strings;
import org.mentalizr.contentManager.exceptions.ContentManagerException;

import java.io.File;
import java.io.IOException;

public class ProgramConf {

    private final String name;
    private final String family;
    private final String title;
    private final String subtitle;
    private final String version;
    private final String author;
    private final String editor;
    private final String copyright;
    private final String license;
    private final String logo;

    public ProgramConf(File programConfFile) throws ContentManagerException {
        ConfigurationFactory configurationFactory = new ConfigurationFactory();
        try {
            configurationFactory.addConfigurationFileFromFilesystem(programConfFile);
            Configuration configuration = configurationFactory.getConfiguration();

            if (!configuration.containsKey("name"))
                throw new ContentManagerException(missingParameterMessage("name", programConfFile));
            this.name = configuration.getString("name");
            if (Strings.isUnspecified(this.name))
                throw new ContentManagerException(missingParameterMessage("name", programConfFile));

            this.family = configuration.containsKey("family") ?
                    configuration.getString("family") : "";

            if (!configuration.containsKey("title"))
                throw new ContentManagerException(missingParameterMessage("title", programConfFile));
            this.title = configuration.getString("title");
            if (Strings.isUnspecified(this.name))
                throw new ContentManagerException(missingParameterMessage("title", programConfFile));

            this.subtitle = configuration.containsKey("subtitle") ?
                    configuration.getString("subtitle") : "";

            this.version = configuration.containsKey("version") ?
                    configuration.getString("version") : "";

            this.author = configuration.containsKey("author") ?
                    configuration.getString("author") : "";

            this.editor = configuration.containsKey("editor") ?
                    configuration.getString("editor") : "";

            this.copyright = configuration.containsKey("copyright") ?
                    configuration.getString("copyright") : "";

            this.license = configuration.containsKey("license") ?
                    configuration.getString("license") : "";

            this.logo = configuration.containsKey("logo") ?
                    configuration.getString("logo") : "";

        } catch (ConfigurationFileNotFoundException | IOException e) {
            throw new ContentManagerException(e);
        }
    }

    private String missingParameterMessage(String parameter, File programConfFile) {
        return "Parameter [" + parameter + "] not set in configuration [" + programConfFile.getAbsolutePath() + "].";
    }

    public String getName() {
        return name;
    }

    public String getFamily() {
        return family;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getVersion() {
        return version;
    }

    public String getAuthor() {
        return author;
    }

    public String getEditor() {
        return editor;
    }

    public String getCopyright() {
        return copyright;
    }

    public String getLicense() {
        return license;
    }

    public String getLogo() {
        return logo;
    }

}
