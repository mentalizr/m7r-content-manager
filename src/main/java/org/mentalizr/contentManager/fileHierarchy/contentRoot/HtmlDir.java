package org.mentalizr.contentManager.fileHierarchy.contentRoot;

import org.mentalizr.contentManager.exceptions.FileNotFoundException;
import org.mentalizr.contentManager.exceptions.ProgramManagerException;
import org.mentalizr.contentManager.fileHierarchy.RepoDirectory;
import org.mentalizr.contentManager.fileHierarchy.contentFile.HtmlFile;
import org.mentalizr.contentManager.fileHierarchy.info.InfoDirHtml;
import org.mentalizr.contentManager.fileHierarchy.module.ModuleDirFileFilter;
import org.mentalizr.contentManager.fileHierarchy.module.ModuleDirHtml;
import org.mentalizr.serviceObjects.frontend.program.Infotext;
import org.mentalizr.serviceObjects.frontend.program.Module;
import org.mentalizr.serviceObjects.frontend.program.Program;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HtmlDir extends RepoDirectory implements ContentRootDir {

    public static final String DIR_NAME = "html";

    private final ProgramConfFile programConfFile;
    private final InfoDirHtml infotextDirHtml;
    private final List<ModuleDirHtml> moduleDirList;
    private final List<HtmlFile> htmlFiles;
    private final Program program;

    public HtmlDir(File file) throws ProgramManagerException {
        super(file);
        this.programConfFile = new ProgramConfFile(new File(getFile(), "program.conf"));
        this.infotextDirHtml = new InfoDirHtml(new File(getFile(), "_info"));
        this.moduleDirList = obtainModuleDirs();
        this.htmlFiles = obtainContentFiles();
        this.program = prepareProgram();
    }

    public String getDisplayName() {
        return this.programConfFile.getName();
    }

    @Override
    public List<HtmlFile> getStepContentFiles() {
        return this.htmlFiles;
    }

    @Override
    public ProgramConfFile getProgramConfFile() {
        return programConfFile;
    }

    @Override
    public InfoDirHtml getInfotextDir() {
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
            modules.add(moduleDirHtml.getModule());
        }

        List<Infotext> infotextList = this.infotextDirHtml.asInfotextList();

        Program program = new Program(
                getName(),
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
