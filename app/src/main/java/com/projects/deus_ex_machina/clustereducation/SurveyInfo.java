package com.projects.deus_ex_machina.clustereducation;

/**
 * Created by Nikita Kiselov on 23-Dec-17.
 * ClusterEducation
 */

public class SurveyInfo {
    private String title;
    private String introMessage;
    private int numberOfQuestions;
    private String timeOfCreating;
    private int participants;

    public SurveyInfo(String title, String introMessage, int numberOfQuestions, String timeOfCreating, int participants) {
        this.title = title;
        this.introMessage = introMessage;
        this.numberOfQuestions = numberOfQuestions;
        this.timeOfCreating = timeOfCreating;
        this.participants = participants;
    }

    public SurveyInfo() {
        //Needed for Firebase
    }

    public String getTitle() {
        return title;
    }

    public String getIntroMessage() {
        return introMessage;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public String getTimeOfCreating() {
        return timeOfCreating;
    }

    public int getParticipants() {
        return participants;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIntroMessage(String introMessage) {
        this.introMessage = introMessage;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public void setTimeOfCreating(String timeOfCreating) {
        this.timeOfCreating = timeOfCreating;
    }

    public void setParticipants(int participants) {
        this.participants = participants;
    }
}
