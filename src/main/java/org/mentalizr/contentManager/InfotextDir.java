package org.mentalizr.contentManager;

import de.arthurpicht.utils.core.strings.Strings;
import org.mentalizr.serviceObjects.frontend.Infotext;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InfotextDir {

    private static class InfotextFilenameFilter implements FilenameFilter {
        @Override
        public boolean accept(File dir, String name) {
            return (name.endsWith(".mdp"));
        }
    }

    private final File infotextDir;
    private final ProgramDir programDir;

    public InfotextDir(File infotextDir, ProgramDir programDir) throws ProgramManagerException {
        this.infotextDir = infotextDir;
        this.programDir = programDir;

        List<Infotext> infotextList = programDir.getProgram().getInfotexts();
        List<File> infotextFiles = getInfotextFiles();
        for (File infotextFile : infotextFiles) {
            String id = getIdForFile(infotextFile);
            String name;
            try {
                name = getNameFromHtmlTag(infotextFile);
            } catch (IOException e) {
                throw new ProgramManagerException(e);
            }
            infotextList.add(new Infotext(id, name));
        }
    }

    private String getIdForFile(File infotextFile) {
        return this.programDir.getProgramId() + "__info_" + Strings.cutEnd(infotextFile.getName(), 4);
    }

    private List<File> getInfotextFiles() {
        File[] infotextFiles = this.infotextDir.listFiles(new InfotextFilenameFilter());
        if (infotextFiles == null || infotextFiles.length == 0)
            return new ArrayList<>();
        return Arrays.asList(infotextFiles);
    }

    private String getNameFromHtmlTag(File htmlFile) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(htmlFile));

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
        return "unknown";
    }

}
