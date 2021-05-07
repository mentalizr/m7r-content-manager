package org.mentalizr.contentManager.build;

import de.arthurpicht.utils.core.collection.Sets;
import org.mentalizr.contentManager.Program;
import org.mentalizr.contentManager.buildHandler.BuildHandler;
import org.mentalizr.contentManager.buildHandler.BuildHandlerException;
import org.mentalizr.contentManager.buildHandler.BuildHandlerFactory;
import org.mentalizr.contentManager.exceptions.ContentManagerException;
import org.mentalizr.contentManager.fileHierarchy.exceptions.MediaResourceReferenceInconsistencyException;
import org.mentalizr.contentManager.fileHierarchy.levels.contentFile.MdpFile;
import org.mentalizr.contentManager.fileHierarchy.levels.program.ProgramDir;
import org.mentalizr.contentManager.helper.SetHelper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MediaResourceCheckProcessor {

//    public void checkExistenceOfReferencedMediaResources(ProgramDir programDir, BuildSummary buildSummary, BuildHandlerFactory buildHandlerFactory) throws ContentManagerException {
//        Set<String> availableMediaResources = programDir.getMediaDir().getAllMediaResourceNames();
//        List<MdpFile> mdpFiles = programDir.getMdpFiles();
//
//        for (MdpFile mdpFile : mdpFiles) {
//            checkReferencedMediaFiles(mdpFile, buildSummary, availableMediaResources, buildHandlerFactory);
//        }
//    }


//    public static Set<String> getReferencedMediaFiles(Program program, BuildHandlerFactory buildHandlerFactory) throws ContentManagerException {
//        ProgramDir programDir = program.getProgramDir();
//        BuildProcessor.assertHtmlDir(programDir);
//
//        List<MdpFile> mdpFiles = programDir.getMdpFiles();
//        Set<String> referencesMediaResources = new HashSet<>();
//
//        for (MdpFile mdpFile : mdpFiles) {
//            Set<String> mediaFileOfSingleMdpFile;
//            try {
//                BuildHandler buildHandler = buildHandlerFactory.createBuildHandler(program, mdpFile);
//                mediaFileOfSingleMdpFile = buildHandler.getReferencedMediaResources();
//            } catch (BuildHandlerException e) {
//                throw new ContentManagerException(e);
//            }
//            referencesMediaResources.addAll(mediaFileOfSingleMdpFile);
//        }
//
//        return referencesMediaResources;
//    }

//    public static void checkReferencedMediaFiles(
//            MdpFile mdpFile,
//            BuildSummary buildSummary,
//            Set<String> availableMediaResources,
//            BuildHandlerFactory buildHandlerFactory) {
//
//        BuildHandler buildHandler = buildHandlerFactory.createBuildHandler(mdpFile);
//        try {
//            Set<String> referencedMediaFiles = buildHandler.getReferencedMediaResources();
//            Set<String> deReferencedMediaFiles = SetHelper.subtract(referencedMediaFiles, availableMediaResources);
//            for (String mediaFile : deReferencedMediaFiles) {
//                BuildException buildException = new BuildException(mdpFile.asPath(), "Referenced media file is not available: [" + mediaFile + "].");
//                buildSummary.addFailedMdpFiles(mdpFile, buildException);
//            }
//        } catch (BuildException e) {
//            buildSummary.addFailedMdpFiles(mdpFile, e);
//        }
//    }

//    public static void checkMediaReferenceIntegrity(Program program, Set<String> referencedMediaFiles) throws ContentManagerException {
//
//        Set<String> availableMediaResources = program.getAllMediaResourceNames();
//
//        Set<String> deReferencedMediaFiles = SetHelper.subtract(referencedMediaFiles, availableMediaResources);
//        if (!deReferencedMediaFiles.isEmpty()) {
//            String mediaFile = Sets.getSomeElement(deReferencedMediaFiles);
//            throw new MediaResourceReferenceInconsistencyException(mediaFile);
//        }
//    }

}
