package com.example.backend.Util.Recommend;

import com.example.backend.Entity.Activity.Activity;

import java.util.Comparator;

public class RecommendHelper implements Comparable<RecommendHelper> {
    private Activity activity;
    private double simularity;

    @Override
    public int compareTo(RecommendHelper r) {
        if (this.getSimularity() < r.getSimularity()) {
            return 1;
        } else {
            return -1;
        }
    }
    public RecommendHelper(Activity activity, double simularity) {
        this.activity = activity;
        this.simularity = simularity;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public double getSimularity() {
        return simularity;
    }

    public void setSimularity(double simularity) {
        this.simularity = simularity;
    }
}
