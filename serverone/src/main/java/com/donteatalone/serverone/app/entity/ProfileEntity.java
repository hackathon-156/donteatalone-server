package com.donteatalone.serverone.app.entity;

public class ProfileEntity {
    private int user_id;
    private String emailID;
    private boolean gender;
    private float geoLat;
    private float geoLong;
    private int breakS;
    private int breakE;
    private String cuisines;
    private int budget;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public float getGeoLat() {
        return geoLat;
    }

    public void setGeoLat(float geoLat) {
        this.geoLat = geoLat;
    }

    public float getGeoLong() {
        return geoLong;
    }

    public void setGeoLong(float geoLong) {
        this.geoLong = geoLong;
    }

    public int getBreakS() {
        return breakS;
    }

    public void setBreakS(int breakS) {
        this.breakS = breakS;
    }

    public int getBreakE() {
        return breakE;
    }

    public void setBreakE(int breakE) {
        this.breakE = breakE;
    }

    public String getCuisines() {
        return cuisines;
    }

    public void setCuisines(String cuisines) {
        this.cuisines = cuisines;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }
}
