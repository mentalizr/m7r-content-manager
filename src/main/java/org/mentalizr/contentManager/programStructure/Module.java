package org.mentalizr.contentManager.programStructure;

import java.util.List;

public record Module(String id, String name, List<Submodule> submodules) {}
