package org.mentalizr.contentManager.programStructure;

import java.util.List;

public record Submodule(String id, String name, List<Step> steps) {}
