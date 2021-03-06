package org.mentalizr.contentManager.fileHierarchy.infotext;

import de.arthurpicht.utils.core.strings.Strings;
import org.mentalizr.contentManager.exceptions.ProgramManagerException;
import org.mentalizr.contentManager.fileHierarchy.RepoDirectory;
import org.mentalizr.contentManager.fileHierarchy.contentFile.HtmlFile;
import org.mentalizr.contentManager.fileHierarchy.contentFile.MdpFileFilter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InfotextDirHtml extends RepoDirectory implements InfotextDir {

    private final List<HtmlFile> infotextFiles;

    public InfotextDirHtml(File file) throws ProgramManagerException {
        super(file);
        this.infotextFiles = obtainInfotextFiles();
    }

    public List<HtmlFile> getInfotextFiles() {
        return infotextFiles;
    }

    private List<HtmlFile> obtainInfotextFiles() throws ProgramManagerException {
        File[] fileArray = this.file.listFiles(new MdpFileFilter());
        if (fileArray == null || fileArray.length == 0)
            return new ArrayList<>();

        List<HtmlFile> htmlFileList = new ArrayList<>();
        for (File file : fileArray) {
            HtmlFile htmlFile = new HtmlFile(file);
            htmlFileList.add(htmlFile);
        }
        return htmlFileList;
    }



//    public InfotextDir(MdpDir mdpDir) throws ProgramManagerException {
//        this.programDir = mdpDir.getProgramDir();
//        this.infotextDir = new FhFile(mdpDir.getDirectory(), "_info");
//
//        List<Infotext> infotextList = programDir.getProgram().getInfotexts();
//        List<FhFile> infotextFhFiles = getInfotextFiles();
//        for (FhFile infotextFhFile : infotextFhFiles) {
//            String id = getIdForFile(infotextFhFile);
//            String name;
//            try {
//                name = getNameFromHtmlTag(infotextFhFile);
//            } catch (IOException e) {
//                throw new ProgramManagerException(e);
//            }
//            infotextList.add(new Infotext(id, name));
//        }
//    }

//    private String getIdForFile(FhFile infotextFhFile) {
//        return this.programDir.getProgramId() + "__info_" + Strings.cutEnd(infotextFhFile.getName(), 4);
//    }

//    private String getDisplayName() throws ProgramManagerException {
//        try {
//            BufferedReader bufferedReader = new BufferedReader(new FileReader(this.file));
//
//            String token = "@@name=";
//            String line = bufferedReader.readLine();
//            int lineNr = 1;
//            while (line != null && lineNr < 5) {
//                if (line.contains(token)) {
//                    String[] splitString = Strings.splitAtDelimiter(line, token);
//                    return splitString[1];
//                }
//                lineNr++;
//                line = bufferedReader.readLine();
//            }
//            throw new ProgramManagerException("Syntax error in .mdp file. Tag @@name not found. [" + this.file.getAbsolutePath() + "]");
//        } catch (IOException e) {
//            throw new ProgramManagerException("IOException when accessing .html file: [" + this.file.getAbsolutePath() + "]", e);
//        }
//    }

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
}
