package org.mentalizr.contentManager.fileHierarchy.contentRoot;

import org.mentalizr.contentManager.exceptions.FileNotFoundException;
import org.mentalizr.contentManager.exceptions.ProgramManagerException;
import org.mentalizr.contentManager.fileHierarchy.ContentTreeDirectory;
import org.mentalizr.contentManager.fileHierarchy.contentFile.HtmlFile;
import org.mentalizr.contentManager.fileHierarchy.infopage.InfopageDirHtml;
import org.mentalizr.contentManager.fileHierarchy.module.ModuleDirFileFilter;
import org.mentalizr.contentManager.fileHierarchy.module.ModuleDirHtml;
import org.mentalizr.serviceObjects.frontend.program.Infotext;
import org.mentalizr.serviceObjects.frontend.program.Module;
import org.mentalizr.serviceObjects.frontend.program.Program;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.mentalizr.contentManager.helper.PathAssertions.assertFileName;

public class HtmlDir extends ContentTreeDirectory implements ContentRootDir {

    public static final String DIR_NAME = "html";

    private final ProgramConfFile programConfFile;
    private final InfopageDirHtml infotextDirHtml;
    private final List<ModuleDirHtml> moduleDirList;
    private final List<HtmlFile> htmlFiles;
    private final Program program;

    public HtmlDir(File file) throws ProgramManagerException {
        super(file);
        assertFileName(file.toPath(), DIR_NAME);
        this.programConfFile = new ProgramConfFile(new File(asFile(), ProgramConfFile.FILE_NAME));
        this.infotextDirHtml = new InfopageDirHtml(new File(asFile(), "_info"));
        this.moduleDirList = obtainModuleDirs();
        this.htmlFiles = obtainContentFiles();
        this.program = prepareProgram();
    }

    public String getDisplayName() {
        return this.programConfFile.getProgramConf().getName();
    }

    @Override
    public List<HtmlFile> getContentFiles() {
        return this.htmlFiles;
    }

    @Override
    public ProgramConfFile getProgramConfFile() {
        return programConfFile;
    }

    @Override
    public InfopageDirHtml getInfotextDir() {
        return this.infotextDirHtml;
    }

    @Override
    public List<ModuleDirHtml> getModuleDirs() {
        return moduleDirList;
    }

    @Override
    public List<String> getModuleDirNames() {
        List<String> dirNames = new ArrayList<>();
        for (ModuleDirHtml moduleDir : this.moduleDirList) {
            dirNames.add(moduleDir.getName());
        }
        return dirNames;
    }

    @Override
    public boolean hasModuleDir(String dirName) {
        for (ModuleDirHtml moduleDir : this.moduleDirList) {
            if (moduleDir.getName().equals(dirName)) return true;
        }
        return false;
    }

    @Override
    public ModuleDirHtml getModuleDir(String dirName) {
        for (ModuleDirHtml moduleDirHtml : this.moduleDirList) {
            if (moduleDirHtml.getName().equals(dirName)) return moduleDirHtml;
        }
        throw new RuntimeException("No Module in html directory with directory name: " + dirName);
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

    public Program asProgram() {
        return this.program;
    }

    private List<ModuleDirHtml> obtainModuleDirs() throws ProgramManagerException {

        File[] fileArray = this.file.listFiles(new ModuleDirFileFilter());
        if (fileArray == null || fileArray.length == 0)
            throw new FileNotFoundException("No modules found: [" + this.file.getAbsolutePath() + "]");

        List<ModuleDirHtml> moduleDirList = new ArrayList<>();
        for (File file : fileArray) {
            ModuleDirHtml moduleDir = new ModuleDirHtml(file);
            moduleDirList.add(moduleDir);
        }

        //noinspection ComparatorCombinators
        moduleDirList.sort((moduleDir1, moduleDir2) -> moduleDir1.getName().compareTo(moduleDir2.getName()));

        return moduleDirList;
    }


    private Program prepareProgram() {
        List<Module> modules = new ArrayList<>();
        for (ModuleDirHtml moduleDirHtml : this.moduleDirList) {
            modules.add(moduleDirHtml.asModule());
        }

        List<Infotext> infotextList = this.infotextDirHtml.asInfotextList();

        Program program = new Program(
                getId(),
                getDisplayName(),
                modules
        );
        program.setInfotexts(infotextList);

        return program;
    }

    private List<HtmlFile> obtainContentFiles() {
        List<HtmlFile> contentFiles = new ArrayList<>();
        for (ModuleDirHtml moduleDir : this.moduleDirList) {
            contentFiles.addAll(moduleDir.getContentFiles());
        }
        return contentFiles;
    }

}
