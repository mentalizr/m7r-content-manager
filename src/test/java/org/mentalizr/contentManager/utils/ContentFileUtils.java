package org.mentalizr.contentManager.utils;

import org.mentalizr.contentManager.fileHierarchy.contentFile.ContentFile;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class ContentFileUtils {

    public static boolean containsFileName(List<? extends ContentFile> contentFiles, String contentName) {
        return (contentFiles.stream()
                .map(ContentFile::getFile)
                .map(File::getName)
                .collect(Collectors.toList())
                .contains(contentName));
    }

    public static boolean containsId(List<? extends ContentFile> contentFiles, String contentName) {
        return (contentFiles.stream()
                .map(ContentFile::getId)
                .collect(Collectors.toList())
                .contains(contentName));
    }

}
