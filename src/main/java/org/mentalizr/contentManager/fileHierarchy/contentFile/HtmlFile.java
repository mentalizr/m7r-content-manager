package org.mentalizr.contentManager.fileHierarchy.contentFile;

import de.arthurpicht.utils.core.strings.Strings;
import org.mentalizr.contentManager.exceptions.ProgramManagerException;
import org.mentalizr.serviceObjects.frontend.program.Infotext;
import org.mentalizr.serviceObjects.frontend.program.Step;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class HtmlFile extends ContentFile {

    public static final String FILETYPE = ".html";

    private final String name;
    private final String displayName;

    public HtmlFile(File file) throws ProgramManagerException {
        super(file);
        this.name = Strings.cutEnd(super.getName(), FILETYPE.length());
        this.displayName = obtainDisplayName();
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

    @Override
    public String getName() {
        return this.name;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public Step asStep() {
        return new Step(getId(), getDisplayName());
    }

    public Infotext asInfotext() {
        return new Infotext(getId(), getDisplayName());
    }

    private String obtainDisplayName() throws ProgramManagerException {
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
