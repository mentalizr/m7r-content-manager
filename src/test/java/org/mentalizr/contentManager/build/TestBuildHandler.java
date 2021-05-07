package org.mentalizr.contentManager.build;

import org.mentalizr.contentManager.Program;
import org.mentalizr.contentManager.buildHandler.BuildHandler;
import org.mentalizr.contentManager.fileHierarchy.levels.contentFile.MdpFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TestBuildHandler implements BuildHandler {

    private final MdpFile mdpFile;

    public TestBuildHandler(Program program, MdpFile mdpFile) {
        this.mdpFile = mdpFile;
    }

    @Override
    public List<String> compile() {
        String displayName = this.mdpFile.getDisplayName();

        List<String> html = new ArrayList<>();
        html.add("<!--");
        html.add("@@name=" + displayName);
        html.add("-->");
        html.add("");
        html.add("<h3>html stub</h3>");
        html.add("");
        html.add("<h5>Built by " + TestBuildHandler.class.getCanonicalName() + " for test purposes only.</h5>");
        html.add("<h5>Corresponding .mdp file: " + this.mdpFile.asPath().toAbsolutePath() + "</h5>");

        return html;
    }

    @Override
    public Set<String> getReferencedMediaResources() {
        return Set.of();
    }

}