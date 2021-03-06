package org.mentalizr.contentManager.fileHierarchy.contentFile;

import de.arthurpicht.utils.core.strings.Strings;
import org.mentalizr.contentManager.exceptions.ProgramManagerException;
import org.mentalizr.contentManager.fileHierarchy.RepoFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class HtmlFile extends RepoFile implements ContentFile {

    public static final String FILETYPE = ".html";

    public HtmlFile(File file) throws ProgramManagerException {
        super(file);
    }

    @Override
    protected String getFiletype() {
        return FILETYPE;
    }

    @Override
    public boolean requiresExistence() {
        return true;
    }

    @Override
    public boolean requiresReadPermission() {
        return true;
    }

    @Override
    public boolean requiresWritePermission() {
        return false;
    }

    public String getDisplayName() throws ProgramManagerException {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(this.file));

            String token = "@@name=";
            String line = bufferedReader.readLine();
            int lineNr = 1;
            while (line != null && lineNr < 5) {
                if (line.contains(token)) {
                    String[] splitString = Strings.splitAtDelimiter(line, token);
                    return splitString[1];
                }
                lineNr++;
                line = bufferedReader.readLine();
            }
            throw new ProgramManagerException("Syntax error in .mdp file. Tag @@name not found. [" + this.file.getAbsolutePath() + "]");
        } catch (IOException e) {
            throw new ProgramManagerException("IOException when accessing .html file: [" + this.file.getAbsolutePath() + "]", e);
        }
    }

}
