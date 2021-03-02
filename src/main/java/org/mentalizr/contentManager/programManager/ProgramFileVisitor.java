package org.mentalizr.contentManager.programManager;

import de.arthurpicht.utils.core.strings.Strings;
import org.mentalizr.serviceObjects.frontend.*;
import org.mentalizr.serviceObjects.frontend.Module;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;

public class ProgramFileVisitor extends SimpleFileVisitor<Path> {

    private static Logger logger = LoggerFactory.getLogger(ProgramFileVisitor.class);

    private Map<String, Program> programMap;
    private File contentRoot;

    private Program lastProgram;
    private Module lastModule;
    private Submodule lastSubmodule;

    public ProgramFileVisitor(Map<String, Program> programMap, File contentRoot) {
        this.programMap = programMap;
        this.contentRoot = contentRoot;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {

        Path relativePath = contentRoot.toPath().relativize(dir);

        logger.debug("previsit: " + dir.toString() + " relative: " + relativePath.toString() + " exist? " + dir.toFile().exists());

        if (isProgramLevel(relativePath)) {

            String programId = relativePath.getName(0).toString();

            Program program = new Program();
            program.setId(programId);
            program.setModules(new ArrayList<>());

            programMap.put(programId, program);
            lastProgram = program;

            logger.debug("Found program: " + programId + " " + relativePath.toString());

        } else if (isModuleLevel(relativePath)) {

            String moduleId = relativePath.getName(1).toString();

            Module module = new Module();
            module.setId(this.lastProgram.getId() + "_" + moduleId);
            module.setSubmodules(new ArrayList<>());

            lastProgram.getModules().add(module);
            lastModule = module;

            logger.debug("Found module: " + moduleId + " " + relativePath.toString());

        } else if (isSubModuleLevel(relativePath)) {

            String submoduleId = relativePath.getName(2).toString();

            Submodule submodule = new Submodule();
            submodule.setId(this.lastModule.getId() + "_" + submoduleId);
            submodule.setSteps(new ArrayList<>());

            lastModule.getSubmodules().add(submodule);
            lastSubmodule = submodule;

            logger.debug("Found submodule: " + submoduleId + " " + relativePath.toString());

        } else if (isInfotextLevel(relativePath)) {

            lastProgram.setInfotexts(new ArrayList<>());

            logger.debug("Found infotexts. " + relativePath.toString());
        }

        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException e) {

        // Aufruf wenn alle Dateien im spez. Verzeichnis besucht wurden

        Path relativePath = contentRoot.toPath().relativize(dir);

        if (isProgramLevel(relativePath)) {
            this.lastProgram.getModules().sort(new ModuleComparator());

        } else if (isModuleLevel(relativePath)) {
            this.lastModule.getSubmodules().sort(new SubmoduleComparator());

        } else if (isSubModuleLevel(relativePath)) {
            this.lastSubmodule.getSteps().sort(new StepComparator());

        } else if (isInfotextLevel(relativePath)) {
            this.lastProgram.getInfotexts().sort(new InfoTextComparator());
        }

        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes) throws IOException {

        Path relativePath = contentRoot.toPath().relativize(path);
        String fileName = relativePath.getFileName().toString();
        int dirLevel = relativePath.getNameCount();

        if (isPrgramConfFile(fileName, dirLevel)) {
            String programName = getNameFromPropertyFile(path);
            lastProgram.setName(programName);

        } else if (isModuleConfFile(fileName, dirLevel)) {
            String moduleName = getNameFromPropertyFile(path);
            lastModule.setName(moduleName);

        } else if (isSubmoduleConfFile(fileName, dirLevel)) {
            String submoduleName = getNameFromPropertyFile(path);
            lastSubmodule.setName(submoduleName);

        } else if (isStepContentHtmlFile(fileName, relativePath)) {
            String id = Strings.cutEnd(fileName, 5);

            Step step = new Step();
            step.setId(this.lastSubmodule.getId() + "_" + id);

            String stepName = getNameFromHtmlTag(path);
            step.setName(stepName);

            lastSubmodule.getSteps().add(step);

        } else if (isInfotextContentHtmlFile(fileName, relativePath)) {
            String id = Strings.cutEnd(fileName, 5);

            Infotext infoText = new Infotext();
            infoText.setId(this.lastProgram.getId() + "__info_" + id);

            String infotextName = getNameFromHtmlTag(path);
            infoText.setName(infotextName);

            this.lastProgram.getInfotexts().add(infoText);

        }

        return FileVisitResult.CONTINUE;
    }

    private boolean isProgramLevel(Path relativePath) {
        return (!relativePath.toString().equals("") && relativePath.getNameCount() == 1);
    }

    private boolean isModuleLevel(Path relativePath) {
        return relativePath.getNameCount() == 2 && !relativePath.getName(1).toString().startsWith("_");
    }

    private boolean isInfotextLevel(Path relativePath) {
        return relativePath.getNameCount() == 2 && relativePath.getName(1).toString().startsWith("_");
    }

    private boolean isSubModuleLevel(Path relativePath) {
        return relativePath.getNameCount() == 3;
    }

    private boolean isPrgramConfFile(String fileName, int dirLevel) {
        return fileName.equals("program.conf") && dirLevel == 2;
    }

    private boolean isModuleConfFile(String fileName, int dirLevel) {
        return fileName.equals("module.conf") && dirLevel == 3;
    }

    private boolean isSubmoduleConfFile(String fileName, int dirLevel) {
        return fileName.equals("submodule.conf") && dirLevel == 4;
    }

    private boolean isStepContentHtmlFile(String fileName, Path relativePath) {
        return fileName.endsWith(".html") && relativePath.getNameCount() == 4;
    }

    private boolean isInfotextContentHtmlFile(String fileName, Path relativePath) {
        if (relativePath.getNameCount() != 3) return false;
        if (!relativePath.getName(1).toString().startsWith("_")) return false;
        return fileName.endsWith(".html");
    }

    private String getNameFromPropertyFile(Path propertyFilePath) throws IOException {
        File propertyFile = propertyFilePath.toFile();
        Properties properties = new Properties();
        properties.load(new InputStreamReader(new FileInputStream(propertyFile), StandardCharsets.UTF_8));
        return properties.getProperty("name");
    }

    private String getNameFromHtmlTag(Path htmlFile) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(htmlFile.toFile()));

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
