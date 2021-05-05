package org.mentalizr.contentManager.build;

import org.mentalizr.contentManager.fileHierarchy.levels.contentFile.MdpFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TestBuildHandler implements BuildHandler {

    @Override
    public List<String> compile(MdpFile mdpFile) {
        String displayName = mdpFile.getDisplayName();

        List<String> html = new ArrayList<>();
        html.add("<!--");
        html.add("@@name=" + displayName);
        html.add("-->");
        html.add("");
        html.add("<h3>html stub</h3>");
        html.add("");
        html.add("<h5>Built by " + TestBuildHandler.class.getCanonicalName() + " for test purposes only.</h5>");
        html.add("<h5>Corresponding .mdp file: " + mdpFile.asPath().toAbsolutePath().toString() + "</h5>");

        return html;
    }

    @Override
    public Set<String> getReferencedMediaResources(MdpFile mdpFile) throws BuildException {
        return Set.of();
    }

}