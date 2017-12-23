package com.projects.deus_ex_machina.clustereducation;

/**
 * Created by Nikita Kiselov on 11-Dec-17.
 * ClusterEducation
 */

public class PollClass {
    private String title;
    private String subtitle;

    public PollClass(String title, String subtitle) {
        this.title = title;
        this.subtitle = subtitle;
    }

    public PollClass() {
    }


    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
}
