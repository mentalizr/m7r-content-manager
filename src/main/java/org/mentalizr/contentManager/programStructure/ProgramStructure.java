package org.mentalizr.contentManager.programStructure;

import java.util.List;

public record ProgramStructure(String id, String name, List<Module> modules, List<Infotext> infotexts) {}
