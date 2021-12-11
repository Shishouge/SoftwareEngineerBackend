package com.example.backend.Entity.Activity;

public class EmotionAnalysis {
    private String EMO_ANALYSIS;
    private String cloud;

    public EmotionAnalysis(String histogram, String cloud) {
        EMO_ANALYSIS = histogram;
        this.cloud = cloud;
    }

    public String getEMO_ANALYSIS() {
        return EMO_ANALYSIS;
    }

    public void setEMO_ANALYSIS(String EMO_ANALYSIS) {
        this.EMO_ANALYSIS = EMO_ANALYSIS;
    }

    public String getCloud() {
        return cloud;
    }

    public void setCloud(String cloud) {
        this.cloud = cloud;
    }
}
