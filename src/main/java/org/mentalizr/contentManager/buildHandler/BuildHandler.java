package org.mentalizr.contentManager.buildHandler;

import java.util.List;
import java.util.Set;

public interface BuildHandler {

    List<String> compile() throws BuildHandlerException;

    Set<String> getReferencedMediaResources() throws BuildHandlerException;

}
