package org.mentalizr.contentManager.programStructure;

public class Step {

    private final String id;
    private final String name;
    private final boolean exercise;
    private final boolean feedback;

    public Step(String id, String name, boolean exercise, boolean feedback) {
        this.id = id;
        this.name = name;
        this.exercise = exercise;
        this.feedback = feedback;
    }

    public String getId() {
        return id;
    }

//    public void setId(String id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

//    public void setName(String name) {
//        this.name = name;
//    }


    public boolean isExercise() {
        return exercise;
    }

    public boolean isFeedback() {
        return feedback;
    }

//    public void setFeedback(boolean feedback) {
//        this.feedback = feedback;
//    }
}
