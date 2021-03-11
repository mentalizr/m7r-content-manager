//package org.mentalizr.contentManager.fileHierarchy;
//
//import de.arthurpicht.utils.core.strings.Strings;
//import org.mentalizr.contentManager.exceptions.ProgramManagerException;
//import org.mentalizr.serviceObjects.frontend.program.Infotext;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.FilenameFilter;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public class InfotextDirMuted {
//
//    private static class InfotextFilenameFilter implements FilenameFilter {
//        @Override
//        public boolean accept(FhFile dir, String name) {
//            return (name.endsWith(".mdp"));
//        }
//    }
//
//    private final ProgramDir programDir;
//    private final FhFile infotextDir;
//
//    public InfotextDirMuted(MdpDir mdpDir) throws ProgramManagerException {
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
//
//    private String getIdForFile(FhFile infotextFhFile) {
//        return this.programDir.getProgramId() + "__info_" + Strings.cutEnd(infotextFhFile.getName(), 4);
//    }
//
//    private List<FhFile> getInfotextFiles() {
//        FhFile[] infotextFhFiles = this.infotextDir.listFiles(new InfotextFilenameFilter());
//        if (infotextFhFiles == null || infotextFhFiles.length == 0)
//            return new ArrayList<>();
//        return Arrays.asList(infotextFhFiles);
//    }
//
//    private String getNameFromHtmlTag(FhFile htmlFhFile) throws IOException {
//        BufferedReader bufferedReader = new BufferedReader(new FileReader(htmlFhFile));
//
//        String token = "@@name=";
//        String line = bufferedReader.readLine();
//        int lineNr = 1;
//        while (line != null && lineNr < 5) {
//            if (line.contains(token)) {
//                String[] splitString = Strings.splitAtDelimiter(line, token);
//                return splitString[1];
//            }
//            lineNr++;
//            line = bufferedReader.readLine();
//        }
//        return "unknown";
//    }
//
//}
