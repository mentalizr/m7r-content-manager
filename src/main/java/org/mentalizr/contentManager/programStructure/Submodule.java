package org.mentalizr.contentManager.programStructure;

import java.util.List;

public class Submodule {

    private String id;
    private String name;
    private List<Step> steps;

    public Submodule() {}

    public Submodule(String id, String name, List<Step> steps) {
        this.id = id;
        this.name = name;
        this.steps = steps;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }
}
