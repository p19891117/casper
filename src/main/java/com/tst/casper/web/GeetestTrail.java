package com.tst.casper.web;

public class GeetestTrail {
    private String id;
    private Integer deltaX;
    private String trailArray;

    private Integer failureTimes;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getDeltaX() {
        return deltaX;
    }

    public void setDeltaX(Integer deltaX) {
        this.deltaX = deltaX;
    }

    public String getTrailArray() {
        return trailArray;
    }

    public void setTrailArray(String trailArray) {
        this.trailArray = trailArray;
    }

    public int getFailureTimes() {
        return failureTimes;
    }

    public void setFailureTimes(int failureTimes) {
        this.failureTimes = failureTimes;
    }

}
