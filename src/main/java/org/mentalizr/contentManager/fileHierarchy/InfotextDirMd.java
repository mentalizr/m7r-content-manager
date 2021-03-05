package org.mentalizr.contentManager.fileHierarchy;

import org.mentalizr.contentManager.exceptions.ProgramManagerException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class InfotextDirMd extends FhDirectory {

    private List<MdpFile> infotextFiles;

    public InfotextDirMd(File file) throws ProgramManagerException {
        super(file);
        this.infotextFiles = obtainInfotextFiles();
    }

    public List<MdpFile> getInfotextFiles() {
        return infotextFiles;
    }

    private List<MdpFile> obtainInfotextFiles() throws ProgramManagerException {
        File[] fileArray = this.file.listFiles(new MdpFilenameFilter());
        if (fileArray == null || fileArray.length == 0)
            return new ArrayList<>();

        List<MdpFile> mdpFileList = new ArrayList<>();
        for (File file : fileArray) {
            MdpFile mdpFile = new MdpFile(file);
            mdpFileList.add(mdpFile);
        }
        return mdpFileList;
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
