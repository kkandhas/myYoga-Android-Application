package com.project.android.myyoga.model;

/**
 * Created by thaik on 4/27/2017.
 */

public class YogaAsana {
    private int id;
    private String yogaName;
    private String yogaLevel;
    private String youtubeValue;
    private String yogaDescription;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYogaName() {
        return yogaName;
    }

    public void setYogaName(String yogaName) {
        this.yogaName = yogaName;
    }

    public String getYogaLevel() {
        return yogaLevel;
    }

    public void setYogaLevel(String yogaLevel) {
        this.yogaLevel = yogaLevel;
    }

    public String getYoutubeValue() {
        return youtubeValue;
    }

    public void setYoutubeValue(String youtubeValue) {
        this.youtubeValue = youtubeValue;
    }

    public String getYogaDescription() {
        return yogaDescription;
    }

    public void setYogaDescription(String yogaDescription) {
        this.yogaDescription = yogaDescription;
    }
}
